package com.matharsa.banking.controller;

import com.matharsa.banking.model.Transaction;
import com.matharsa.banking.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/banking")
@CrossOrigin(origins = "*")
public class TransactionController {

    private static final String MAKE_WEBHOOK_URL = "https://hook.eu1.make.com/846l75q0hggc31oca8ugs8l5inpwgzuf";
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

        if (transactionRepository.count() == 0) {
            transactionRepository.save(new Transaction("Initial Repository Core Activation Deposit", 1000.00, 1000.00));
        }
    }

    @GetMapping("/ledger")
    public List<Transaction> getLedgerHistory() {
        return transactionRepository.findAll();
    }

    @GetMapping("/balance")
    public double getBalance() {
        Double balance = transactionRepository.getCurrentBalance();
        return balance != null ? balance : 0.0;
    }

    @PostMapping("/deposit")
    public Transaction makeDeposit(@RequestParam double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit volume must be greater than zero");
        double currentBal = getBalance();
        double newBal = currentBal + amount;

        Transaction tx = new Transaction("ATM Cash / Wire Transfer Deposit", amount, newBal);
        return transactionRepository.save(tx);
    }

    @PostMapping("/withdraw")
    public Transaction makeWithdrawal(@RequestParam double amount) {
        double currentBal = getBalance();
        if (amount <= 0 || amount > currentBal) throw new IllegalArgumentException("Insufficient funds or invalid transaction volume parameters");
        double newBal = currentBal - amount;

        Transaction tx = new Transaction("ATM Electronic Cash Withdrawal", -amount, newBal);

        // 🚀 Asynchronously stream transaction alerts straight to the Make cloud pipeline
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MAKE_WEBHOOK_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        String.format("{\"description\":\"%s\",\"amount\":%.2f,\"runningBalance\":%.2f}",
                                tx.getDescription(), tx.getAmount(), tx.getRunningBalance())))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        return transactionRepository.save(tx);
    }
}
