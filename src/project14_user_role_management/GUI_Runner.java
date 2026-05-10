package project14_user_role_management;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;   // For creating the text file
import java.io.IOException;  // For handling "what if" errors (like a full disk)

public class GUI_Runner {
    public static void main(String[] args) {
        // --- WINDOW SETUP ---
        JFrame frame = new JFrame("Project 14: Secure Access Logger");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // --- TOP PANEL: INPUTS ---
        JPanel controlPanel = new JPanel();

        // Role Dropdown
        JComboBox<Role> roleMenu = new JComboBox<>(Role.values());

        // Password Field (Uses dots to hide text)
        JPasswordField passField = new JPasswordField(8);

        JButton checkButton = new JButton("Attempt Access");

        controlPanel.add(new JLabel("Role:"));
        controlPanel.add(roleMenu);
        controlPanel.add(new JLabel("Password:"));
        controlPanel.add(passField);
        controlPanel.add(checkButton); // NEW: Create the Clear button and add it to the top panel
        JButton clearButton = new JButton("Clear All");
        controlPanel.add(clearButton);
// Added for after Attempt Access button
        JButton saveButton = new JButton("Save Logs");
        controlPanel.add(saveButton);

        // --- CENTER: THE LOG AREA ---
        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Clean look for logs
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("System Security Audit Trail"));

        // --- BUTTON LOGIC (EVENT HANDLING) ---
        checkButton.addActionListener(e -> {
            Role selectedRole = (Role) roleMenu.getSelectedItem();
            String enteredPassword = new String(passField.getPassword());
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            // Logic: Only Admins with the password 'admin123' get full success
            if (selectedRole == Role.ADMIN && enteredPassword.equals("admin123")) {
                logArea.append("[" + time + "] SUCCESS: Admin authenticated. Full access granted. ✅\n");
            }
            else if (selectedRole == Role.ADMIN) {
                logArea.append("[" + time + "] ALERT: Admin login attempted with WRONG password! ❌\n");
            }
            else {
                // For other roles,  just log their entry as guests
                logArea.append("[" + time + "] INFO: " + selectedRole + " logged in (Guest View). ℹ️\n");
            }

            // Security Best Practice: Clear the password field after clicking
            passField.setText("");
        });
// NEW LOGIC for the Save Button (New add)JButton saveButton = new JButton("Save Logs");controlPanel.add(saveButton);
       saveButton.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("security_audit.txt")) {
                writer.write(logArea.getText()); // Grabs everything from the log
                JOptionPane.showMessageDialog(frame, "Audit report saved! ✅");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error: Could not save file. ❌");
            }
        });
        // --- FINALIZE ---
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); // Centers window on screen
        // NEW LOGIC: Clear All with Confirmation
        clearButton.addActionListener(e -> {
            // WHAT: A Yes/No Confirmation Dialog
            // WHY: To prevent accidental deletion of logs
            int response = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to delete ALL logs?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            // If the user clicks "YES"
            if (response == JOptionPane.YES_OPTION) {
                logArea.setText(""); // This clears the entire text area
                JOptionPane.showMessageDialog(frame, "Logs have been cleared! 🧹");
            }
        });
        frame.setVisible(true);
    }
}

