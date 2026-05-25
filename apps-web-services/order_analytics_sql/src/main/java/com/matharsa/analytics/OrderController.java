package com.matharsa.analytics;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analytics")
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderRecord> trackOrder(@RequestParam String sku, @RequestParam Integer qty, @RequestParam Double price) {
        OrderRecord record = new OrderRecord(sku, qty, price);
        OrderRecord saved = repository.save(record); // Executes a real INSERT SQL statement
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderRecord>> getAllOrders() {
        List<OrderRecord> records = repository.findAll(); // Executes a real SELECT * FROM statement
        return ResponseEntity.ok(records);
    }
}
