package com.matharsa.gateway.service;


import com.matharsa.gateway.model.TargetServer;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RoutingEngineService {

    private final List<TargetServer> serverPool = new ArrayList<>();
    private final List<TargetServer> flattenedRoutingMatrix = new ArrayList<>();
    private final AtomicInteger requestCounter = new AtomicInteger(0);

    public RoutingEngineService() {
        // Seed our network cluster pool with 3 virtual servers carrying different capacities
        serverPool.add(new TargetServer("Server-Alpha-HighSpec", "http://10.0.0.1:8081", 3));
        serverPool.add(new TargetServer("Server-Beta-MediumSpec", "http://10.0.0.2:8082", 2));
        serverPool.add(new TargetServer("Server-Gamma-LowSpec", "http://10.0.0.3:8083", 1));

        buildFlattenedMatrix();
    }

    // Mathematical preprocessing: Flattens weights into an array list for continuous O(1) execution loops
    private void buildFlattenedMatrix() {
        for (TargetServer server : serverPool) {
            for (int i = 0; i < server.getWeight(); i++) {
                flattenedRoutingMatrix.add(server);
            }
        }
    }

    // 🚀 High-Speed Thread-Safe Routing Selection Rule
    public TargetServer nextAvailableServer() {
        if (flattenedRoutingMatrix.isEmpty()) {
            throw new IllegalStateException("Infrastructure Offline: Target cluster contains zero nodes.");
        }

        // Mathematical constraints: Use a clean modulo arithmetic wrap to safely loop across array indices
        int currentCycleIndex = requestCounter.getAndIncrement() % flattenedRoutingMatrix.size();
        return flattenedRoutingMatrix.get(Math.abs(currentCycleIndex));
    }
}
