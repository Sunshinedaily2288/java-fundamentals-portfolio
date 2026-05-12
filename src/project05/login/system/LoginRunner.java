package project05.login.system;

import javax.swing.SwingUtilities;

public class LoginRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}
