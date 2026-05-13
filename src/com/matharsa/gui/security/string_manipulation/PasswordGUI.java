package string_manipulation;
import javax.swing.*;
import java.awt.*;

    public class PasswordGUI {
        public static void main(String[] args) {
            // 1. Setup
            JFrame frame = new JFrame("Secure Key Gen");
            frame.setLayout(new FlowLayout()); // Simple layout: items flow in a line

            // 2. Components
            JLabel resultLabel = new JLabel("Click to Generate Password");
            JButton genButton = new JButton("Generate");

            // 3. The Logic (What happens)
            genButton.addActionListener(e -> {
                char[] charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%".toCharArray();
                String password = "";
                for (int i = 0; i < 12; i++) {
                    int randomIndex = (int) (Math.random() * charset.length);
                    password += charset[randomIndex];
                }
                // Update the text on the screen
                resultLabel.setText("Password: " + password);
            });

            // 4. together
            frame.add(genButton);
            frame.add(resultLabel);

            frame.setSize(350, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }

