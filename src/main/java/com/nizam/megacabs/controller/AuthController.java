package com.nizam.megacabs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nizam.megacabs.dto.AuthResponse;
import com.nizam.megacabs.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String userEmailId = loginData.get("userEmailId");
        String userPassword = loginData.get("userPassword");

        AuthResponse response = userService.login(userEmailId, userPassword);

        if (response != null) {
            return ResponseEntity.ok(response); // Return 200 OK with the response
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password"); // Return 401 Unauthorized
        }
    }
    //other methods
}
