package com.matharsa.ecommerce.model;

import java.util.List;

public class Product {
    private Long id;
    private String name;
    private double price;
    private String seoSlug; // 🚀 SEO-friendly URL string
    private List<String> tags; // 🚀 Product search tags

    public Product(Long id, String name, double price, String seoSlug, List<String> tags) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.seoSlug = seoSlug;
        this.tags = tags;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getSeoSlug() { return seoSlug; }
    public List<String> getTags() { return tags; }
}
