package com.matharsa.security.model;

public class UserCredentials {
    private final String username;
    private final String hashedPassword;
    private final String cryptographicSalt;

    public UserCredentials(String username, String hashedPassword, String cryptographicSalt) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.cryptographicSalt = cryptographicSalt;
    }

    public String getUsername() { return username; }
    public String getHashedPassword() { return hashedPassword; }
    public String getCryptographicSalt() { return cryptographicSalt; }
}
