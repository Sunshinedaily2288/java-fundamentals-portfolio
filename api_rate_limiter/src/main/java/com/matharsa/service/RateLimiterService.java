package com.matharsa.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private static final long BUCKET_CAPACITY = 5;
    private static final long REFILL_RATE_SECONDS = 10;

    // Inner class to track individual client bucket state parameters
    private static class TokenBucket {
        long tokens = BUCKET_CAPACITY;
        long lastRefillTime = System.currentTimeMillis();
    }

    private final Map<String, TokenBucket> ipBuckets = new ConcurrentHashMap<>();

    public synchronized boolean tryConsume(String ipAddress) {
        TokenBucket bucket = ipBuckets.computeIfAbsent(ipAddress, k -> new TokenBucket());
        refill(bucket);

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true; // Request allowed
        }
        return false; // Throttled!
    }

    public synchronized long getRemainingTokens(String ipAddress) {
        TokenBucket bucket = ipBuckets.get(ipAddress);
        if (bucket == null) return BUCKET_CAPACITY;
        refill(bucket);
        return bucket.tokens;
    }

    private void refill(TokenBucket bucket) {
        long now = System.currentTimeMillis();
        long elapsedTime = now - bucket.lastRefillTime;
        long tokensToAdd = elapsedTime / (REFILL_RATE_SECONDS * 1000);

        if (tokensToAdd > 0) {
            bucket.tokens = Math.min(BUCKET_CAPACITY, bucket.tokens + tokensToAdd);
            bucket.lastRefillTime = now;
        }
    }
}
