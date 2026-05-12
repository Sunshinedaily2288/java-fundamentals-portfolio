package project06.atm.currency.exchange;

public class currency_exchange {
    // 1. Move your rates here so the methods can see them
    private final double USD_TO_EUR = 0.92;
    private final double USD_TO_GBP = 0.79;
    private final double USD_TO_JPY = 151.60;

    // 2. This is the math engine
    public double calculate(double amount, int choice) {
        return switch (choice) {
            case 1 -> amount * USD_TO_EUR;
            case 2 -> amount * USD_TO_GBP;
            case 3 -> amount * USD_TO_JPY;
            default -> 0.0;
        };
    }

    // 3. This gets the currency name
    public String getSymbol(int choice) {
        return switch (choice) {
            case 1 -> "EUR";
            case 2 -> "GBP";
            case 3 -> "JPY";
            default -> "Unknown";
        };
    }
}
