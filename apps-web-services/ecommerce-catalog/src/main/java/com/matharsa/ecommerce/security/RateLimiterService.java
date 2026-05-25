package com.matharsa.ecommerce.security;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    // Thread-safe map storing IP addresses linked to lists of execution timestamps (in milliseconds)
    private final Map<String, List<Long>> requestHistory = new ConcurrentHashMap<>();

    private static final int MAX_REQUESTS = 5;
    private static final long TIME_WINDOW_MS = 10000; // 10 seconds

    public synchronized boolean isAllowed(String ipAddress) {
        long currentTime = System.currentTimeMillis();

        // Compute or fetch existing history list for the target client IP
        requestHistory.putIfAbsent(ipAddress, new ArrayList<>());
        List<Long> timestamps = requestHistory.get(ipAddress);

        // 🔍 Apply math clearing rule: Drop any logs outside our 10-second threshold boundary
        timestamps.removeIf(time -> (currentTime - time) > TIME_WINDOW_MS);

        // Check if client bounds remain within threshold boundaries
        if (timestamps.size() >= MAX_REQUESTS) {
            return false;
        }

        // Add the current clean request metric timestamp
        timestamps.add(currentTime);
        return true;
    }

    public void clearHistory() {
        requestHistory.clear();
    }
}
