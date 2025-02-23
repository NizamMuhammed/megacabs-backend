package com.nizam.megacabs.controller;

import com.nizam.megacabs.model.User;
import com.nizam.megacabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean signIn(@RequestBody User user) {
        return userService.signIn(user);
    }
}
