package project04.atm.banking.platform;

import javax.swing.*;
import java.awt.*;

public class ATMPlatformUI extends JFrame {
    private ATMPlatformTransaction account;
    private JTextField inputField = new JTextField(10);
    private JTextArea outputArea = new JTextArea(5, 20);

    public ATMPlatformUI(ATMPlatformTransaction account) {
        this.account = account;

        // Window Setup
        setTitle("ATM Project 04 - GUI");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Buttons
        JButton btnBalance = new JButton("Check Balance");
        JButton btnWithdraw = new JButton("Withdraw");

        // Button Logic
        btnBalance.addActionListener(e ->
                outputArea.setText("Current Balance: $" + account.getBalance()));

        btnWithdraw.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(inputField.getText());
                if (account.withdraw(amount)) {
                    outputArea.setText("Withdrew: $" + amount + "\nNew Balance: $" + account.getBalance());
                } else {
                    outputArea.setText("Insufficient Funds!");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: Enter a valid number.");
            }
        });

        // Adding components to window
        add(new JLabel("Enter Amount:"));
        add(inputField);
        add(btnBalance);
        add(btnWithdraw);
        add(new JScrollPane(outputArea));

        pack();
        setLocationRelativeTo(null); // Centers the window
        setVisible(true);
    }
}
