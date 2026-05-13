package login_validation;

import javax.swing.SwingUtilities;

public class LoginRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}
