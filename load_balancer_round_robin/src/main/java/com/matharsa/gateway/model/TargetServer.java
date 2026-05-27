package com.matharsa.gateway.model;


public class TargetServer {
    private final String serverId;
    private final String urlAddress;
    private final int weight;

    public TargetServer(String serverId, String urlAddress, int weight) {
        this.serverId = serverId;
        this.urlAddress = urlAddress;
        this.weight = weight;
    }

    public String getServerId() { return serverId; }
    public String getUrlAddress() { return urlAddress; }
    public int getWeight() { return weight; }
}
