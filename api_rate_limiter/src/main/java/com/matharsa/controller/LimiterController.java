package com.matharsa.controller;

import com.matharsa.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/secure")
public class LimiterController {

    private final RateLimiterService rateLimiterService;

    public LimiterController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    // Secure Data Route: http://localhost:8087/api/secure/data
    @GetMapping("/data")
    public ResponseEntity<Map<String, Object>> getSecureData(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        Map<String, Object> response = new HashMap<>();

        // Process request through security shield engine
        if (!rateLimiterService.tryConsume(clientIp)) {
            response.put("status", 429);
            response.put("error", "Too Many Requests");
            response.put("message", "API rate limit exceeded. Shield engaged. Wait for token refill.");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
        }

        response.put("status", 200);
        response.put("message", "Access Granted. Resource payload downloaded successfully.");
        response.put("tokensRemaining", rateLimiterService.getRemainingTokens(clientIp));
        return ResponseEntity.ok(response);
    }
}
