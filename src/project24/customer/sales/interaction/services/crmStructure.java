package project24.customer.sales.interaction.services;

import java.time.LocalDate;

/**
 * Clean data layer isolated here so you can add fields like phone numbers
 * or tracking metrics easily in the future.
 */
record Client(String name, String email) {
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}

record Interaction(String note, LocalDate followUpDate) {
    @Override
    public String toString() {
        return "📅 [" + followUpDate + "] Note: " + note;
    }
}

