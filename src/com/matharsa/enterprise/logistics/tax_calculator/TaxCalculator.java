package tax_calculator;

import java.util.Scanner;

public class TaxCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Project 02: Business Tax & Discount Engine ---");
        System.out.print("Enter Total Sale Amount: $");
        double totalSale = input.nextDouble();

        double discount = 0;
        double taxRate = 0;

        // --- THE LOGIC TIERS (The "Why" and "When") ---
        // Tier 1: "VIP" - Big spenders get the biggest discount
        // Line 18: Update Tier 1 logic
        // Tier 1: Platinum
        if (totalSale >= 5000) {
            discount = 0.20; // 20% off
            taxRate = 0.05;  // 5% Tax
        }
        // Tier 2: Gold. Loyalty (This MUST follow the closing bracket of the IF)
        else if (totalSale >= 500) {
            discount = 0.10;
            taxRate = 0.08;
        }
        // Tier 3: Standard
        else if (totalSale > 0) {
            discount = 0.02;
            taxRate = 0.10;
        }
        // Tier 4: Error
        else {
            System.out.println("Invalid amount.");
            return;
        }
        // --- CALCULATIONS ---
        double discountAmount = totalSale * discount;
        double subtotal = totalSale - discountAmount;
        double taxAmount = subtotal * taxRate;
        double finalTotal = subtotal + taxAmount;

        // --- BUSINESS REPORT ---
        System.out.println("\n--- Final Invoice ---");
        System.out.println("Original Price:  $" + totalSale);
        System.out.println("Discount Applied: -$" + discountAmount + " (" + (discount*100) + "%)");
        System.out.println("Tax Amount:      +$" + taxAmount + " (" + (taxRate*100) + "%)");
        System.out.println("----------------------");
        System.out.println("FINAL TOTAL:     $" + finalTotal);

        input.close();
    }
}

