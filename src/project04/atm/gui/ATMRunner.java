package project04.atm.gui;

import javax.swing.SwingUtilities;

public class ATMRunner {
    public static void main(String[] args) {
        // Create an account with a $1500 starting balance
        ATMAccount myAccount = new ATMAccount(1500.0);

        // This launches the GUI on the correct thread
        SwingUtilities.invokeLater(() -> {
            new ATMGUI(myAccount);
        });
    }
}
