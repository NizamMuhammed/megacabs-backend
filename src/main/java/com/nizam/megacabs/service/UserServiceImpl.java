package com.nizam.megacabs.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder; // Remove @Autowired here

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(User user) throws EmailAlreadyExistsException {
        try {
            Optional<User> existingUser = userRepository.findByUserEmailId(user.getUserEmailId());
            if (existingUser.isPresent()) {
                throw new EmailAlreadyExistsException("Email already exists");
            }

            String rawPassword = user.getUserPassword();
            logger.debug("Raw password before encoding: {}", rawPassword);
            
            // Always encode the password
            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setUserPassword(encodedPassword);
            
            logger.debug("Password encoded successfully");
            
            User savedUser = userRepository.save(user);
            logger.debug("User saved successfully with ID: {}", savedUser.getUserId());
            
            return savedUser;
        } catch (EmailAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            throw new RuntimeException("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public User findByEmail(String userEmailId) {
        Optional<User> userOptional = userRepository.findByUserEmailId(userEmailId);
        return userOptional.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {
        try {
            Optional<User> userOptional = userRepository.findByUserEmailId(userEmailId);
            User user = userOptional.orElseThrow(() -> 
                new UsernameNotFoundException("User not found with email: " + userEmailId));

            logger.debug("Loading user: {}", user.getUserEmailId());
            logger.debug("User roles: {}", user.getRoles());
            logger.debug("Raw stored password: {}", user.getUserPassword());

            String[] roles = user.getRoles().stream()
                .map(role -> "ROLE_" + role.name())  // Add ROLE_ prefix
                .toArray(String[]::new);

            logger.debug("Mapped roles: {}", (Object) roles);

            var userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUserEmailId())
                .password(user.getUserPassword())  // Use stored encoded password
                .authorities(roles)  // Use authorities instead of roles
                .build();
                
            logger.debug("Created UserDetails - Username: {}, Authorities: {}", 
                userDetails.getUsername(),
                userDetails.getAuthorities());
                
            return userDetails;
        } catch (Exception e) {
            logger.error("Error loading user: {}", e.getMessage());
            throw new UsernameNotFoundException("Error loading user: " + e.getMessage());
        }
    }
}
