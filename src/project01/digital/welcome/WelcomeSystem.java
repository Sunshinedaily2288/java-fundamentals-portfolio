package project01.digital.welcome;

import java.util.Scanner;

public class WelcomeSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Project 01: Digital Welcome System ---");

        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.println("Welcome to the team, " + name.toUpperCase() + "! ✨");

        System.out.print("Enter your monthly income: $");
        double income = input.nextDouble();

        double funFund = income * 0.20;
        double savings = income * 0.30;

        System.out.println("\n--- Your Monthly Financial Snapshot ---");
        System.out.println("Entertainment Budget (20%): $" + funFund);
        System.out.println("Business Savings Goal (30%): $" + savings);
        System.out.println("---------------------------------------");

        // --- NEW: Status Message Logic ---
        // WHY: This "Conditional Logic" gives the user personalized advice.
        System.out.print("STATUS: ");
        if (income >= 5000) {
            System.out.println("Excellent! You have a strong foundation for investment. 🚀");
        } else if (income >= 2000) {
            System.out.println("Great job! You are in a stable position to grow. 📈");
        } else {
            System.out.println("Keep pushing! Focus on building your skills and income. 💪");
        }
        System.out.println("---------------------------------------");

        System.out.println("Have a productive day, " + name + "!");

        input.close();
    }
}


