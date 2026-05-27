package com.matharsa.enterprise.logistics.system;

public class Account {
    // 1. Private fields (Encapsulation)
    private String accountNumber;
    private double balance;
    private String accountHolder;

    // 2. Constructor
    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        // Ensure account doesn't start with negative money
        this.balance = Math.max(0, initialBalance);
    }

    // 3. Methods (The "Logic")
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount.");
            return false;
        }
    }

    // 4. Getters (To see the balance without changing it directly)
    public double getBalance() {
        return balance;
    }
}
