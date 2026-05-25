package com.matharsa.security;

import com.matharsa.security.model.UserCredentials;
import com.matharsa.security.service.HashingEngineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashingEngineServiceTest {

    private HashingEngineService service;

    @BeforeEach
    public void setup() {
        service = new HashingEngineService();
    }

    @Test
    public void testSuccessfulUserHashingFlow() {
        UserCredentials creds = service.registerUser("testUser", "SecurePassword999!");
        assertNotNull(creds.getHashedPassword());
        assertNotNull(creds.getCryptographicSalt());

        // Check verification module logic strings
        assertTrue(service.verifyPassword("SecurePassword999!", creds));
        assertFalse(service.verifyPassword("WrongPassword!", creds));
    }

    @Test
    public void testPasswordTooShortThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.registerUser("badUser", "short1");
        }, "Should throw an exception if password parameters sit under the 8 character boundary limit.");
    }
}
