package org.example.web_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This tells Java to scan this package for web features
@SpringBootApplication(scanBasePackages = "org.example")
public class WebMain {
    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }
}

