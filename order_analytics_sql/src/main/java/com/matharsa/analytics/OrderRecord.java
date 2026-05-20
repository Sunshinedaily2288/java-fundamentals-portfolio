package com.matharsa.analytics;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemSku;
    private Integer quantity;
    private Double unitPrice;
    private Double totalRevenue;

    // Default constructor required by JPA
    public OrderRecord() {}

    public OrderRecord(String itemSku, Integer quantity, Double unitPrice) {
        this.itemSku = itemSku;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalRevenue = quantity * unitPrice; // Automated metric calculation
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getItemSku() { return itemSku; }
    public void setItemSku(String itemSku) { this.itemSku = itemSku; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }
}
