package com.nizam.megacabs.controller;

import com.nizam.megacabs.model.User;
import com.nizam.megacabs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from the front-end
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Proceed with registration - the email check is already handled in the service
        User registeredUser = userService.signUp(user);
        return ResponseEntity.ok(registeredUser);
    }

    // Other endpoints (e.g., signIn) can be added here
}
