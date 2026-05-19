package com.matharsa.ecommerce.controller;

import com.matharsa.ecommerce.model.Product;
import com.matharsa.ecommerce.repository.ProductRepository;
import com.matharsa.ecommerce.security.RateLimiterService;
import com.matharsa.ecommerce.exception.RateLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final RateLimiterService rateLimiterService;

    // Dependency injection handles both repositories and security engines smoothly
    public ProductController(ProductRepository productRepository, RateLimiterService rateLimiterService) {
        this.productRepository = productRepository;
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping
    public List<Product> getProducts(
            @RequestParam(required = false) String search,
            HttpServletRequest request) { // Injects standard HTTP network request context wrapper

        String clientIp = request.getRemoteAddr();

        // 🔒 Trigger security evaluation block
        if (!rateLimiterService.isAllowed(clientIp)) {
            throw new RateLimitExceededException("Rate limit exceeded. Maximum 5 requests per 10 seconds allowed.");
        }

        if (search != null && !search.trim().isEmpty()) {
            return productRepository.search(search);
        }
        return productRepository.findAll();
    }

    @GetMapping("/seo/{slug}")
    public ResponseEntity<Product> getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
