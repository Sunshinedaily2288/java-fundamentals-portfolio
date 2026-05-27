package com.matharsa.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.matharsa.cache")
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
        System.out.println("⚡ Caching Service online on http://localhost:8094/api/cache/search?sku=SKU-100");
    }
}
