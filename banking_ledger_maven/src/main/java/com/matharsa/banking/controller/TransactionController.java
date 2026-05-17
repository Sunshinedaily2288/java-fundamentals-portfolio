package com.matharsa.banking.controller;

import com.matharsa.banking.model.Transaction;
import com.matharsa.banking.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/banking")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;

        // Initialize account with a starting deposit balance if the ledger database file is empty on startup
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
        return transactionRepository.save(tx);
    }
}
