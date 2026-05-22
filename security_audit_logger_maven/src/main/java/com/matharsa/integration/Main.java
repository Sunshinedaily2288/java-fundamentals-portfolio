package com.matharsa.integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Java Security Audit Event Generator ===");

        System.out.print("Enter Target Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Event Type (1 for SUCCESS, 2 for FAILED): ");
        String choice = scanner.nextLine();
        String eventType = choice.equals("1") ? "LOGIN_SUCCESS" : "LOGIN_FAILED";

        System.out.print("Enter Source IP Address: ");
        String ipAddress = scanner.nextLine();

        // Build Payload Map
        Map<String, Object> payload = new HashMap<>();
        payload.put("timestamp", Instant.now().toString());
        payload.put("project", "security_audit_logger_maven");
        payload.put("username", username);
        payload.put("event_type", eventType);
        payload.put("ip_address", ipAddress);
        payload.put("client_browser", "IntelliJ-CLI-Agent/1.0");

        // Convert Map to formatted JSON string via Gson
        String jsonPayload = gson.toJson(payload);

        System.out.println("\n🔥 Generated Payload Ready for Make.com Automation Tomorrow:");
        System.out.println(jsonPayload);

        scanner.close();
    }
}
