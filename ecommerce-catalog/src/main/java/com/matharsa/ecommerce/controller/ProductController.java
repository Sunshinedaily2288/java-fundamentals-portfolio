package com.matharsa.ecommerce.controller;

import com.matharsa.ecommerce.model.Product;
import com.matharsa.ecommerce.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 🔍 Unified Endpoint: Handles global fetch AND search queries
    // Example: http://localhost:8085/api/products?search=java
    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String search) {
        if (search != null && !search.trim().isEmpty()) {
            return productRepository.search(search);
        }
        return productRepository.findAll();
    }

    // 🚀 SEO Endpoint: Look up inventory using clean URL patterns instead of raw numerical IDs
    // Example: http://localhost:8085/api/products/seo/sap-gateway-connector
    @GetMapping("/seo/{slug}")
    public ResponseEntity<Product> getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
