package login_validation;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {
    private User userModel = new User();
    private JTextField userField = new JTextField(15);
    private JPasswordField pinField = new JPasswordField(15);

    public LoginGUI() {
        setTitle("Project 05 - Secure Login");
        setLayout(new GridLayout(3, 2, 5, 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String enteredUser = userField.getText();
            String enteredPin = new String(pinField.getPassword());

            if (userModel.checkCredentials(enteredUser, enteredPin)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                // Future: This is where we would trigger Project 04's ATM GUI
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(new JLabel("Username:")); add(userField);
        add(new JLabel("PIN:")); add(pinField);
        add(new JLabel("")); add(loginBtn);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
