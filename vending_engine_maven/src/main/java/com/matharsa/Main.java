package com.matharsa;

import com.matharsa.model.Coin;
import com.matharsa.service.InventoryEngine;
import com.matharsa.service.VendingEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@SpringBootApplication
@RestController
@RequestMapping("/api/vending")
public class Main {

    private static final InventoryEngine inventory = new InventoryEngine();
    private static final VendingEngine machine = new VendingEngine(inventory);

    // Track how many total items have been restocked globally per SKU for step 4
    private static final Map<String, Integer> restockHistoryLog = new HashMap<>();

    static {
        restockHistoryLog.put("A1", 0);
        restockHistoryLog.put("B1", 0);
        restockHistoryLog.put("C1", 0);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/inventory")
    public Map<String, Object> getLiveInventory() {
        Map<String, Object> report = new HashMap<>();
        com.matharsa.repository.MockDatabase db = com.matharsa.repository.MockDatabase.getInstance();

        int totalMachineRevenue = 0;
        Map<String, Object> productsMap = new HashMap<>();
        Map<String, Object> salesBreakdownList = new HashMap<>();

        String[] skus = {"A1", "B1", "C1"};
        for (String sku : skus) {
            com.matharsa.model.Product p = db.findProductBySku(sku);
            int stock = db.getStockCount(sku);
            int unitsSold = db.getSalesCount(sku);
            int itemRevenue = unitsSold * p.getPriceInCents();

            totalMachineRevenue += itemRevenue;

            // Step 3 Table data maps
            Map<String, Object> itemDetails = new HashMap<>();
            itemDetails.put("name", p.getName());
            itemDetails.put("price", p.getPriceInCents() + "¢");
            itemDetails.put("stock", stock);
            itemDetails.put("sold", unitsSold);
            itemDetails.put("earnings", itemRevenue + "¢");
            itemDetails.put("totalRestocked", restockHistoryLog.getOrDefault(sku, 0) + " units");

            productsMap.put(sku, itemDetails);

            // Step 5 Breakdown List itemization
            salesBreakdownList.put(p.getName(), "Sold: " + unitsSold + " | Revenue: " + itemRevenue + "¢");
        }

        report.put("products", productsMap);
        report.put("totalRevenue", totalMachineRevenue + "¢");
        report.put("breakdownList", salesBreakdownList);
        return report;
    }

    @PostMapping("/insert")
    public String insertCoin(@RequestParam Coin coin) {
        machine.insertCoin(coin);
        return "Balance: " + machine.getCurrentBalanceInCents() + "¢ | Inserted " + coin.name();
    }

    @PostMapping("/purchase")
    public String purchaseProduct(@RequestParam String sku) {
        boolean success = machine.purchaseProduct(sku);
        if (success) {
            return "🛒 Dispensing SKU " + sku + "! Click Refund to get remaining change.";
        }
        return "❌ Transaction Failed for " + sku + ". Check balance/stock.";
    }

    @PostMapping("/refund")
    public String refund() {
        List<Coin> change = machine.dispenseChange();
        return "💰 Change Dispensed: " + change.toString();
    }

    @PostMapping("/restock")
    public String restockProduct(@RequestParam String sku, @RequestParam int amount) {
        inventory.addStock(sku, amount);
        restockHistoryLog.put(sku, restockHistoryLog.getOrDefault(sku, 0) + amount);
        return "✅ Restocked SKU " + sku + " by + " + amount + " units!";
    }
}
