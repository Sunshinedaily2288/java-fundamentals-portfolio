package com.matharsa.gui.security.login_validation;

public class User {
    private String username = "Admin";
    private String pin = "1234";

    public boolean checkCredentials(String userEntry, String pinEntry) {
        return username.equals(userEntry) && pin.equals(pinEntry);
    }
}