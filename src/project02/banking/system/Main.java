package project02.banking.system;

public class Main {
    public static void main(String[] args) {
        // This is where you test your com.bank.logic.Account
        Account myAccount = new Account("12345", "Your Name", 500.0);
        System.out.println("Starting Balance: " + myAccount.getBalance());

        myAccount.deposit(200);
        System.out.println("New Balance: " + myAccount.getBalance());
    }
}
