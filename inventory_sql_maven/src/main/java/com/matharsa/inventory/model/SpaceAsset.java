package com.matharsa.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SpaceAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetName;
    private String modelNumber;
    private String department;
    private String conditionStatus;
    private int quantity;

    public SpaceAsset() {}

    public SpaceAsset(String assetName, String modelNumber, String department, String conditionStatus, int quantity) {
        this.assetName = assetName;
        this.modelNumber = modelNumber;
        this.department = department;
        this.conditionStatus = conditionStatus;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    public String getModelNumber() { return modelNumber; }
    public void setModelNumber(String modelNumber) { this.modelNumber = modelNumber; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getConditionStatus() { return conditionStatus; }
    public void setConditionStatus(String conditionStatus) { this.conditionStatus = conditionStatus; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
