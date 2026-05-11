package project18_security_tools;

public class PasswordGenerator {
        public static void main(String[] args) {
            // 1. "Pool" of characters (1D Array)
            char[] charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%".toCharArray();

            int passwordLength = 12;
            String password = "";

            // 2. The Loop: random character for each slot
            for (int i = 0; i < passwordLength; i++) {
                // random index from 0 to the end of array
                int randomIndex = (int) (Math.random() * charset.length);

                // Adding random character to password string
                password += charset[randomIndex];
            }

            System.out.println("--- Secure Key Generator ---");
            System.out.println("Your New Password: " + password);
            System.out.println("----------------------------");
        }
    }

