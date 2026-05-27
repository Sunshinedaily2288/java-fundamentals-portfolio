package com.matharsa.gateway.controller;


import com.matharsa.gateway.model.TargetServer;
import com.matharsa.gateway.service.RoutingEngineService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    private final RoutingEngineService routingEngineService;

    public GatewayController(RoutingEngineService routingEngineService) {
        this.routingEngineService = routingEngineService;
    }

    // Simulated Web Interceptor endpoint
    // Try: http://localhost:8087/api/gateway/route
    @GetMapping("/route")
    public Map<String, String> routeIncomingNetworkTraffic() {
        TargetServer routedNode = routingEngineService.nextAvailableServer();

        Map<String, String> networkPayload = new HashMap<>();
        networkPayload.put("GatewayStatus", "🟢 REQUEST ROUTED SUCCESSFUL");
        networkPayload.put("AssignedNode", routedNode.getServerId());
        networkPayload.put("InternalIP", routedNode.getUrlAddress());
        networkPayload.put("AlgorithmRule", "Weighted Round-Robin Matrix Allotment");

        return networkPayload;
    }
}
