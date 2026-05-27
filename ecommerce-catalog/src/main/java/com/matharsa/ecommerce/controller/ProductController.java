package com.matharsa.ecommerce.controller;

import com.matharsa.ecommerce.model.Product;
import com.matharsa.ecommerce.repository.ProductRepository;
import com.matharsa.ecommerce.security.RateLimiterService;
import com.matharsa.ecommerce.exception.RateLimitExceededException;
import com.matharsa.ecommerce.service.PriceConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final RateLimiterService rateLimiterService;
    private final PriceConversionService priceConversionService;

    public ProductController(ProductRepository productRepository,
                             RateLimiterService rateLimiterService,
                             PriceConversionService priceConversionService) {
        this.productRepository = productRepository;
        this.rateLimiterService = rateLimiterService;
        this.priceConversionService = priceConversionService;
    }

    @GetMapping
    public List<Product> getProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "USD") String currency,
            HttpServletRequest request) {

        String clientIp = request.getRemoteAddr();

        if (!rateLimiterService.isAllowed(clientIp)) {
            throw new RateLimitExceededException("Rate limit exceeded. Maximum 5 requests per 10 seconds allowed.");
        }

        List<Product> products = (search != null && !search.trim().isEmpty()) ?
                productRepository.search(search) : productRepository.findAll();

        if (!"USD".equalsIgnoreCase(currency)) {
            return products.stream()
                    .map(p -> new Product(p.getId(), p.getName(),
                            priceConversionService.convertPrice(p.getPrice(), currency),
                            p.getSeoSlug(), p.getTags()))
                    .collect(Collectors.toList());
        }

        return products;
    }

    @GetMapping("/seo/{slug}")
    public ResponseEntity<Product> getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
