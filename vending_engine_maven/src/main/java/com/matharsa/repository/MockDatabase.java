package com.matharsa.repository;

import com.matharsa.model.Product;
import java.util.HashMap;
import java.util.Map;

public class MockDatabase {
    private static MockDatabase instance;
    private final Map<String, Product> productTable = new HashMap<>();
    private final Map<String, Integer> inventoryTable = new HashMap<>();
    private final Map<String, Integer> salesTable = new HashMap<>();

    private MockDatabase() {
        seedDatabase();
    }

    public static synchronized MockDatabase getInstance() {
        if (instance == null) {
            instance = new MockDatabase();
        }
        return instance;
    }

    private void seedDatabase() {
        productTable.put("A1", new Product("Cola", 75));
        productTable.put("B1", new Product("Chips", 110));
        productTable.put("C1", new Product("Candy Bar", 90));

        inventoryTable.put("A1", 5);
        inventoryTable.put("B1", 1);
        inventoryTable.put("C1", 0);

        salesTable.put("A1", 0);
        salesTable.put("B1", 0);
        salesTable.put("C1", 0);
    }

    public Product findProductBySku(String sku) {
        return productTable.get(sku);
    }

    public int getStockCount(String sku) {
        return inventoryTable.getOrDefault(sku, 0);
    }

    public void updateStock(String sku, int newQuantity) {
        inventoryTable.put(sku, newQuantity);
    }

    public int getSalesCount(String sku) {
        return salesTable.getOrDefault(sku, 0);
    }

    public void recordSale(String sku) {
        salesTable.put(sku, salesTable.getOrDefault(sku, 0) + 1);
    }
}
