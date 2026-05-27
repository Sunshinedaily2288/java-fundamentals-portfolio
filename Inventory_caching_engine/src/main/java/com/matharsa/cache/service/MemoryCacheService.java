package com.matharsa.cache.service;

import com.matharsa.cache.model.InventoryItem;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MemoryCacheService {

    private final Map<String, InventoryItem> cacheMap = new ConcurrentHashMap<>();
    private final Map<String, InventoryItem> databaseDisk = new ConcurrentHashMap<>();

    public MemoryCacheService() {
        databaseDisk.put("SKU-100", new InventoryItem("SKU-100", "Enterprise Server Frame", 15));
        databaseDisk.put("SKU-200", new InventoryItem("SKU-200", "Fibre Channel Optical Switch", 8));
    }

    public Map<String, Object> lookupItem(String itemCode) {
        Map<String, Object> response = new java.util.HashMap<>();
        long startTime = System.currentTimeMillis();

        if (cacheMap.containsKey(itemCode)) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            response.put("LookupStatus", "⚡ CACHE HIT (Fetched from high-speed memory)");
            response.put("ElapsedTimeMs", elapsedTime + "ms");
            response.put("Data", cacheMap.get(itemCode));
            return response;
        }

        try {
            Thread.sleep(1500); // Forces a deliberate 1.5 second lag delay
        } catch (InterruptedException ignored) {}

        InventoryItem diskRecord = databaseDisk.get(itemCode);

        if (diskRecord != null) {
            cacheMap.put(itemCode, diskRecord);
            long elapsedTime = System.currentTimeMillis() - startTime;
            response.put("LookupStatus", "🐌 CACHE MISS (Fetched from slow persistent disk ledger)");
            response.put("ElapsedTimeMs", elapsedTime + "ms");
            response.put("Data", diskRecord);
        } else {
            response.put("LookupStatus", "❌ RECORD NOT FOUND");
        }

        return response;
    }

    public void flushCacheMemory() { cacheMap.clear(); }
}
