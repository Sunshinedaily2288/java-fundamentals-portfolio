package com.matharsa.enterprise.logistics.invoicing;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class InvoiceGUI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<InvoiceItem> basket = new ArrayList<>();

        // 1. COLLECT FULL DETAILS
        System.out.println("--- Professional Invoice Setup ---");
        System.out.print("Client Name: ");
        String client = input.nextLine();

        System.out.print("Client Address: ");
        String clientAddress = input.nextLine();

        System.out.print("Customer ID: ");
        String customerID = input.nextLine();

        System.out.print("Delivery Terms (e.g. Courier, Sea Freight): ");
        String deliveryTerms = input.nextLine();

        String date = LocalDate.now().toString();

        // 2. ITEM COLLECTION
        while (true) {
            System.out.print("\nItem Description (or 'done'): ");
            String desc = input.nextLine();
            if (desc.equalsIgnoreCase("done")) break;

            System.out.print("Quantity: ");
            int q = input.nextInt();
            System.out.print("Unit Price: ");
            double p = input.nextDouble();
            input.nextLine(); // Clear buffer

            basket.add(new InvoiceItem(desc, q, p));
        }

        // 3. GENERATE COMPLETE DOCUMENT
        try (PrintWriter writer = new PrintWriter(new FileWriter(client + "_Professional_Invoice.txt"))) {
            // COMPANY HEADER
            writer.println("=============================================================");
            writer.println("                JAVA DEVELOPER PORTFOLIO LTD.                ");
            writer.println("                123 Code Street, Vienna, Austria             ");
            writer.println("                Email: billing@portfolio.com                 ");
            writer.println("=============================================================");

            // CLIENT & METADATA
            writer.println("BILL TO: " + client.toUpperCase());
            writer.println("ADDRESS: " + clientAddress);
            writer.println("-------------------------------------------------------------");
            writer.printf("CUSTOMER NO: %-15s | DATE: %s\n", customerID, date);
            writer.println("DELIVERY   : " + deliveryTerms);
            writer.println("-------------------------------------------------------------");

            // TABLE
            writer.printf("| %-20s | %-5s | %-10s | %-10s |\n", "DESCRIPTION", "QTY", "UNIT", "NET");
            writer.println("-------------------------------------------------------------");

            double subtotal = 0;
            for (InvoiceItem i : basket) {
                writer.printf("| %-20s | %-5d | $%-9.2f | $%-9.2f |\n",
                        i.description, i.qty, i.unitPrice, i.getNetPrice());
                subtotal += i.getNetPrice();
            }

            double tax = subtotal * 0.05;
            writer.println("-------------------------------------------------------------");
            writer.printf("%-43s $%-9.2f\n", "SUBTOTAL (NET):", subtotal);
            writer.printf("%-43s $%-9.2f\n", "VAT (5%):", tax);
            writer.printf("%-43s $%-9.2f\n", "GRAND TOTAL (GROSS):", (subtotal + tax));
            writer.println("=============================================================");

            // SIGNATURE PORTION
            writer.println("\n\n");
            writer.println("   _________________________          _________________________");
            writer.println("      Authorized Signature                Customer Signature   ");
            writer.println("\n   Thank you for your business! Please quote INV-" + customerID + " in payments.");

            System.out.println("\nSUCCESS! Complete Invoice generated for " + client);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


