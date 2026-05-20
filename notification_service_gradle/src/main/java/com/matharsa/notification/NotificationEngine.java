package com.matharsa.notification;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.concurrent.*;

@Service
public class NotificationEngine {
    private final BlockingQueue<NotificationEvent> queue = new LinkedBlockingQueue<>(5000);
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private volatile boolean running = true;

    public record NotificationEvent(String tenantId, String recipient, String message, String channel) {}

    public boolean enqueue(NotificationEvent event) {
        return queue.offer(event);
    }

    @PostConstruct
    public void startWorkers() {
        for (int i = 0; i < 3; i++) {
            executor.submit(this::processQueue);
        }
    }

    private void processQueue() {
        while (running || !queue.isEmpty()) {
            try {
                NotificationEvent event = queue.poll(500, TimeUnit.MILLISECONDS);
                if (event != null) {
                    Thread.sleep(100);
                    System.out.printf("[Worker-%s] Sent %s to %s via %s\n",
                            Thread.currentThread().getName(), event.tenantId(), event.recipient(), event.channel());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @PreDestroy
    public void shutdown() {
        this.running = false;
        executor.shutdown();
    }
}
