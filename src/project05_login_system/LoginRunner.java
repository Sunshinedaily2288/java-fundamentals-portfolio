package project05_login_system;

import javax.swing.SwingUtilities;

public class LoginRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}
