package com.matharsa.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {
    private final NotificationEngine engine;

    public NotificationController(NotificationEngine engine) {
        this.engine = engine;
    }

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationEngine.NotificationEvent event) {
        boolean accepted = engine.enqueue(event);
        if (accepted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Event queued successfully");
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("System queue full");
    }
}
