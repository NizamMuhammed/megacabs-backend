package com.nizam.megacabs.controller;

import com.nizam.megacabs.model.User;
import com.nizam.megacabs.service.UserServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from the front-end
@Slf4j
public class UserController {

    @Autowired
    private UserServiceimpl userService;

    @PostMapping("signup")
    public ResponseEntity<User> signUp(@RequestBody User user){
        return  ResponseEntity.ok(userService.signUp(user));
    }
    @GetMapping("signing/{userEmailId}/{userPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String userEmailId,@PathVariable String userPassword){
        return ResponseEntity.ok(userService.signIn(userEmailId,userPassword));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers(); // Assumes getAllUsers method in UserServiceimpl
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
    }
