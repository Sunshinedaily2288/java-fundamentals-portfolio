package com.matharsa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime; // Remains null until vehicle leaves
    private BigDecimal calculatedFee;

    // Constructors
    public Ticket() {}

    public Ticket(Long id, String licensePlate, LocalDateTime entryTime, LocalDateTime exitTime, BigDecimal calculatedFee) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.calculatedFee = calculatedFee;
    }

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }

    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }

    public BigDecimal getCalculatedFee() { return calculatedFee; }
    public void setCalculatedFee(BigDecimal calculatedFee) { this.calculatedFee = calculatedFee; }
}
