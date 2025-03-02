package com.nizam.megacabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nizam.megacabs.model.User;
import com.nizam.megacabs.service.UserService;

@RestController
@RequestMapping("/api/v1/users") // Updated base path for UserController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register") // Updated path to /api/v1/users/register
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
           User userSaved= userService.signUp(user); // Changed here
            return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
