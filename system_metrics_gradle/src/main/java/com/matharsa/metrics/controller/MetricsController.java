package com.matharsa.metrics.controller;

import com.matharsa.metrics.model.SystemTelemetry;
import com.matharsa.metrics.service.TelemetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    private final TelemetryService telemetryService;

    public MetricsController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    // Try: http://localhost:8095/api/metrics/live
    @GetMapping("/live")
    public SystemTelemetry getRealTimeSystemPerformance() {
        return telemetryService.captureLiveTelemetry();
    }
}
