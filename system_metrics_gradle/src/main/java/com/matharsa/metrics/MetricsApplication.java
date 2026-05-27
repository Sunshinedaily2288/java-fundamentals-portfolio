package com.matharsa.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.matharsa.metrics")
public class MetricsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetricsApplication.class, args);
        System.out.println("🐘 Gradle Telemetry Engine active on http://localhost:8095/api/metrics/live");
    }
}
