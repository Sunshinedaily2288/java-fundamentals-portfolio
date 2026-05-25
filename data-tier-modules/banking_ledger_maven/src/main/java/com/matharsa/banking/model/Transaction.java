package com.matharsa.banking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private double amount;
    private double runningBalance;
    private LocalDateTime timestamp;

    public Transaction() {}

    public Transaction(String description, double amount, double runningBalance) {
        this.description = description;
        this.amount = amount;
        this.runningBalance = runningBalance;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public double getRunningBalance() { return runningBalance; }
    public void setRunningBalance(double runningBalance) { this.runningBalance = runningBalance; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
