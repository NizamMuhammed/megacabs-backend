package com.nizam.megacabs.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nizam.megacabs.dto.AuthResponse;
import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import com.nizam.megacabs.model.Role;
import com.nizam.megacabs.model.User;
import com.nizam.megacabs.service.EmailService;
import com.nizam.megacabs.service.UserService;
import com.nizam.megacabs.util.JwtUtil;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String userEmailId = loginData.get("userEmailId");
            String userPassword = loginData.get("userPassword");

            logger.debug("Login attempt for email: {}", userEmailId);

            if (userEmailId == null || userPassword == null) {
                return ResponseEntity.badRequest().body("Email and password are required");
            }

            try {
                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEmailId, userPassword)
                );

                if (authentication.isAuthenticated()) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userEmailId);
                    String token = jwtUtil.generateToken(userDetails);
                    User user = userService.findByEmail(userEmailId);
                    
                    if (user == null) {
                        logger.error("User not found after authentication: {}", userEmailId);
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                    }
                    
                    logger.debug("User authenticated successfully: {}", userEmailId);
                    logger.debug("User roles: {}", user.getRoles());
                    
                    AuthResponse response = new AuthResponse(
                        user.getUserName(), 
                        token, 
                        user.getUserId(), 
                        user.getRoles()
                    );
                    return ResponseEntity.ok(response);
                }
            } catch (Exception e) {
                logger.error("Authentication error for user {}: {}", userEmailId, e.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.error("Login error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Login failed: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                user.getRoles().add(Role.CUSTOMER);
            }
            
            // Validate required fields
            if (user.getUserEmailId() == null || user.getUserPassword() == null || user.getUserName() == null) {
                return ResponseEntity.badRequest().body("Email, password and username are required");
            }
            
            // Store raw password for email before it gets encoded
            String rawPassword = user.getUserPassword();
            
            User registeredUser = userService.signUp(user);
            
            // Send email if user is a driver - use raw password for email
            if (user.getRoles().contains(Role.DRIVER)) {
                try {
                    logger.debug("Sending email with raw password to driver");
                    emailService.sendDriverCredentials(user.getUserEmailId(), rawPassword);
                } catch (Exception e) {
                    logger.error("Failed to send email: {}", e.getMessage());
                }
            }
            
            return ResponseEntity.ok(registeredUser);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("Email already exists");
        } catch (Exception e) {
            logger.error("Registration error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Registration failed: " + e.getMessage());
        }
    }
}
