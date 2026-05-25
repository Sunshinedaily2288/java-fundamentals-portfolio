package com.matharsa.security.service;

import com.matharsa.security.model.UserCredentials;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashingEngineService {

    public String generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public UserCredentials registerUser(String username, String rawPassword) {
        if (rawPassword == null || rawPassword.trim().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }

        String salt = generateRandomSalt();
        String combinedInput = rawPassword + salt;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(combinedInput.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return new UserCredentials(username, hexString.toString(), salt);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Subsystem error: SHA-256 unavailable.", e);
        }
    }

    public boolean verifyPassword(String inputPassword, UserCredentials storedCredentials) {
        String combinedInput = inputPassword + storedCredentials.getCryptographicSalt();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(combinedInput.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString().equals(storedCredentials.getHashedPassword());
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }
}
