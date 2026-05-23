package com.matharsa.service;

import com.matharsa.model.Coin;
import com.matharsa.model.Product;
import java.util.ArrayList;
import java.util.List;

public class VendingEngine {
    private final InventoryEngine inventory;
    private int currentBalanceInCents = 0;

    public VendingEngine(InventoryEngine inventory) {
        this.inventory = inventory;
    }

    public void insertCoin(Coin coin) {
        this.currentBalanceInCents += coin.getValue();
        System.out.println("Inserted: " + coin.name() + " | Current Balance: " + currentBalanceInCents + "¢");
    }

    public boolean purchaseProduct(String sku) {
        Product product = inventory.getProduct(sku);

        if (product == null) {
            System.out.println("❌ ERROR: Invalid selection code '" + sku + "'");
            return false;
        }

        if (!inventory.isAvailable(sku)) {
            System.out.println("❌ ERROR: " + product.getName() + " (" + sku + ") is out of stock.");
            return false;
        }

        if (currentBalanceInCents < product.getPriceInCents()) {
            int missing = product.getPriceInCents() - currentBalanceInCents;
            System.out.println("❌ ERROR: Insufficient funds for " + product.getName() + ". Needs " + missing + "¢ more.");
            return false;
        }

        inventory.deductStock(sku);
        com.matharsa.repository.MockDatabase.getInstance().recordSale(sku);
        currentBalanceInCents -= product.getPriceInCents();
        System.out.println("🛒 SUCCESS: Dispensing " + product.getName());
        return true;
    }

    public List<Coin> dispenseChange() {
        List<Coin> change = new ArrayList<>();
        int changeDue = currentBalanceInCents;

        for (Coin coin : new Coin[]{Coin.DOLLAR, Coin.QUARTER, Coin.DIME, Coin.NICKEL}) {
            while (changeDue >= coin.getValue()) {
                change.add(coin);
                changeDue -= coin.getValue();
            }
        }
        currentBalanceInCents = 0;
        return change;
    }

    public int getCurrentBalanceInCents() {
        return this.currentBalanceInCents;
    }
}
