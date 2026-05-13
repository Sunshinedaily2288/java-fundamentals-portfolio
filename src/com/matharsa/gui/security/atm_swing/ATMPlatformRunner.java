package atm_swing;

import javax.swing.SwingUtilities;

public class ATMPlatformRunner {
    public static void main(String[] args) {
        // Create an account with a $1500 starting balance
        ATMPlatformTransaction myAccount = new ATMPlatformTransaction(1500.0);

        // This launches the GUI on the correct thread
        SwingUtilities.invokeLater(() -> {
            new ATMPlatformUI(myAccount);
        });
    }
}
