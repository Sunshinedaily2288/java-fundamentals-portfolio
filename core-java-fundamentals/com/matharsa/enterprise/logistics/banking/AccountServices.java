package com.matharsa.enterprise.logistics.banking;

public class AccountServices {
    private double balance;

    public AccountServices(double initialBalance) {
        this.balance = initialBalance;
    }

    public double fetchBalance() {
        return balance;
    }

    public void processWithdrawal(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Processing... Withdrawal successful. Current balance: $" + balance);
        } else {
            System.out.println("Transaction Failed: Insufficient funds.");
        }
    }
}

