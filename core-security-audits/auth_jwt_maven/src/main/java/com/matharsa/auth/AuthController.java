package com.matharsa.auth;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final JwtBroker jwtBroker;

    public AuthController(JwtBroker jwtBroker) {
        this.jwtBroker = jwtBroker;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username) {
        // Simulating role assignments for our system portfolio
        Map<String, Object> claims = Map.of("role", "ROLE_ADMIN", "tier", "premium");
        String token = jwtBroker.generateToken(username, claims);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/verify")
    public ResponseEntity<Map<String, Object>> verify(@RequestHeader(value = "Authorization", required = false) String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing or invalid Bearer prefix"));
        }
        try {
            String token = header.substring(7);
            Claims claims = jwtBroker.validateAndExtractClaims(token);

            return ResponseEntity.ok(Map.of(
                    "subject", claims.getSubject(),
                    "role", claims.get("role"),
                    "tier", claims.get("tier"),
                    "expiration", claims.getExpiration().toString()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }
}
