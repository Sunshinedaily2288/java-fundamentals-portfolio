package com.matharsa.metrics.service;

import com.matharsa.metrics.model.SystemTelemetry;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class TelemetryService {

    private final Random random = new Random();

    // Simulates reading low-level operating system resource matrices dynamically
    public SystemTelemetry captureLiveTelemetry() {
        double simulatedCpu = 15.0 + (random.nextDouble() * 65.0); // Random CPU metric between 15% and 80%
        long simulatedMemory = 4000000000L + (random.nextInt(12) * 1000000000L); // Free RAM fluctuations
        int simulatedConnections = 50 + random.nextInt(450); // Live socket hits

        // Apply mathematical constraint: Round the CPU double cleanly to 1 decimal place
        double roundedCpu = Math.round(simulatedCpu * 10.0) / 10.0;

        return new SystemTelemetry(roundedCpu, simulatedMemory, simulatedConnections);
    }
}
