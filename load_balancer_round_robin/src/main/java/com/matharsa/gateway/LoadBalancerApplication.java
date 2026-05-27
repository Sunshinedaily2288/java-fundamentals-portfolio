package com.matharsa.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.matharsa.gateway")
public class LoadBalancerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoadBalancerApplication.class, args);
        System.out.println("🚦 Gateway Cluster Simulator active on http://localhost:8087/api/gateway/route");
    }
}
