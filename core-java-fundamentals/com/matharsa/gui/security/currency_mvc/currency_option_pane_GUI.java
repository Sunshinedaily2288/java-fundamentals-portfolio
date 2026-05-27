package com.matharsa.gui.security.currency_mvc;

import javax.swing.JOptionPane;

public class currency_option_pane_GUI {

    private currency_exchange engine = new currency_exchange();

        public void display() {
            // Step 1: Input Amount
            String amountInput = JOptionPane.showInputDialog(null,
                    "Enter amount in USD:", "ATM Currency Exchange", JOptionPane.QUESTION_MESSAGE);

            if (amountInput == null || amountInput.isEmpty()) return;
            double amount = Double.parseDouble(amountInput);

            // Step 2: Input Choice
            String menu = "Choose Currency:\n1. EUR (Euro)\n2. GBP (Pound)\n3. JPY (Yen)";
            String choiceInput = JOptionPane.showInputDialog(null, menu, "Select Currency", JOptionPane.PLAIN_MESSAGE);

            if (choiceInput == null || choiceInput.isEmpty()) return;
            int choice = Integer.parseInt(choiceInput);

            // Step 3: Get Results from Engine
            double result = engine.calculate(amount, choice);
            String symbol = engine.getSymbol(choice);

            // Step 4: Show Final Result
            String finalMessage = String.format("Conversion Successful!\n$%.2f USD = %.2f %s", amount, result, symbol);
            JOptionPane.showMessageDialog(null, finalMessage, "Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }
