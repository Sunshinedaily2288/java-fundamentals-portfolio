package com.matharsa.gui.security.role_access_control;

public class Main {
    public static void main(String[] args) {
        User testUser = new User("Dev_Student", Role.ADMIN, "admin123");

        System.out.println("--- Console Test ---");
        System.out.println("User: " + testUser.getUsername());
        System.out.println("Role: " + testUser.getRole());

        // Simple logic test
        if (testUser.getRole() == Role.ADMIN) {
            System.out.println("Status: Full Access Granted.");
        }
    }
}