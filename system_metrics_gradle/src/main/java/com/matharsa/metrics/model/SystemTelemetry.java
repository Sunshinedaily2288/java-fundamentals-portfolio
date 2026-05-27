package com.matharsa.metrics.model;

public class SystemTelemetry {
    private final double cpuUsagePercentage;
    private final long freeMemoryBytes;
    private final int activeConnections;

    public SystemTelemetry(double cpuUsagePercentage, long freeMemoryBytes, int activeConnections) {
        this.cpuUsagePercentage = cpuUsagePercentage;
        this.freeMemoryBytes = freeMemoryBytes;
        this.activeConnections = activeConnections;
    }

    public double getCpuUsagePercentage() { return cpuUsagePercentage; }
    public long getFreeMemoryBytes() { return freeMemoryBytes; }
    public int getActiveConnections() { return activeConnections; }
}
