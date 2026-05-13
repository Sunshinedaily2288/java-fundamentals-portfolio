package project03.atm.banking;

public class AccountServiceRunner {
    public static void main(String[] args) {
        AccountServices service = new AccountServices(2000.0);

        System.out.println("--- ATM Banking Service ---");
        System.out.println("Opening Balance: $" + service.fetchBalance());

        service.processWithdrawal(500.0);
    }
}

