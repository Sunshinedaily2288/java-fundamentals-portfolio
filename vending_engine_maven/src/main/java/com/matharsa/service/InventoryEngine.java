package com.matharsa.service;

import com.matharsa.model.Product;
import com.matharsa.repository.MockDatabase;

public class InventoryEngine {
    private final MockDatabase db = MockDatabase.getInstance();

    public boolean isAvailable(String sku) {
        return db.getStockCount(sku) > 0;
    }

    public Product getProduct(String sku) {
        return db.findProductBySku(sku);
    }

    public void deductStock(String sku) {
        if (isAvailable(sku)) {
            int currentStock = db.getStockCount(sku);
            db.updateStock(sku, currentStock - 1);
            System.out.println("[DB UPDATE] SKU " + sku + " stock decreased to " + (currentStock - 1));
        }
    }
    public void addStock(String sku, int amount) {
        if (amount > 0) {
            int currentStock = db.getStockCount(sku);
            db.updateStock(sku, currentStock + amount);
            System.out.println("[DB UPDATE] SKU " + sku + " restocked by " + amount + ". New stock: " + (currentStock + amount));
        }
    }
}
