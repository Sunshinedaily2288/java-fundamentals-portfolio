package com.matharsa.ecommerce.test.security;

import com.matharsa.ecommerce.security.RateLimiterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RateLimiterServiceTest {

    private RateLimiterService rateLimiterService;
    private final String mockClientIp = "192.168.1.50";

    @BeforeEach
    public void setup() {
        rateLimiterService = new RateLimiterService();
        rateLimiterService.clearHistory();
    }

    @Test
    public void testSequentialRequestsWithinThresholdAllowed() {
        // First 5 requests must pass without issues
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiterService.isAllowed(mockClientIp), "Request " + (i + 1) + " should be approved.");
        }
    }

    @Test
    public void testSixthRequestViolatingBoundaryThrowsFalse() {
        // Execute maximum capacity allotment bounds
        for (int i = 0; i < 5; i++) {
            rateLimiterService.isAllowed(mockClientIp);
        }
        // The 6th concurrent request within the same millisecond block must get blocked
        assertFalse(rateLimiterService.isAllowed(mockClientIp), "Sixth request must be denied by gateway policy.");
    }
}
