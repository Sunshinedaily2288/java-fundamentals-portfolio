package com.matharsa.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.matharsa.security")
public class CryptoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);
        System.out.println("🛡️ Cryptographic Web Engine active on http://localhost:8086/api/crypto/hash?username=test&password=YourPassword123");
    }
}
