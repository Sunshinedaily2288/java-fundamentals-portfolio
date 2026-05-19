package com.matharsa.security.controller;

import com.matharsa.security.model.UserCredentials;
import com.matharsa.security.service.HashingEngineService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final HashingEngineService hashingEngineService = new HashingEngineService();

    @GetMapping("/hash")
    public UserCredentials getSecureHash(
            @RequestParam String username,
            @RequestParam String password) {
        return hashingEngineService.registerUser(username, password);
    }
}
