package com.matharsa.ecommerce.repository;

import com.matharsa.ecommerce.model.Product;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final Map<Long, Product> database = new HashMap<>();

    public ProductRepository() {
        // Seed inventory data with explicit IDs, names, prices, SEO slugs, and tagging arrays
        database.put(1L, new Product(1L, "Enterprise Java Book", 49.99, "enterprise-java-book", Arrays.asList("java", "backend", "education")));
        database.put(2L, new Product(2L, "SAP Gateway Connector", 129.50, "sap-gateway-connector", Arrays.asList("sap", "integration", "enterprise")));
        database.put(3L, new Product(3L, "Spring Boot Microservice Engine", 79.00, "spring-boot-engine", Arrays.asList("java", "spring", "backend")));
    }

    public List<Product> findAll() {
        return new ArrayList<>(database.values());
    }

    // 🔍 Feature: Search by keyword matching title or tags
    public List<Product> search(String query) {
        if (query == null) return findAll();
        String lowerQuery = query.toLowerCase().trim();
        return database.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerQuery) ||
                        p.getTags().stream().anyMatch(t -> t.toLowerCase().contains(lowerQuery)))
                .collect(Collectors.toList());
    }

    // 🚀 SEO Feature: Look up product explicitly by its clean URL slug
    public Optional<Product> findBySlug(String slug) {
        if (slug == null) return Optional.empty();
        return database.values().stream()
                .filter(p -> p.getSeoSlug().equalsIgnoreCase(slug.trim()))
                .findFirst();
    }
}
