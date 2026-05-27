package com.matharsa.cache.model;

public class InventoryItem {
    private final String itemCode;
    private final String name;
    private final int stockQuantity;

    public InventoryItem(String itemCode, String name, int stockQuantity) {
        this.itemCode = itemCode;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public String getItemCode() { return itemCode; }
    public String getName() { return name; }
    public int getStockQuantity() { return stockQuantity; }
}
