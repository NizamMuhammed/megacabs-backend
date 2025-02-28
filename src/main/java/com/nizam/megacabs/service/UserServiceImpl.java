package com.nizam.megacabs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.dto.AuthResponse;
import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User signUp(User user) throws EmailAlreadyExistsException {
        Optional<User> existingUser = userRepository.findByUserEmailId(user.getUserEmailId());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(String userEmailId, String userPassword) {
        // Authentication logic (validate credentials, generate JWT, etc.)
        AuthResponse response = new AuthResponse();
        return response;
    }
    
    @Override
    public User findByEmail(String userEmailId) {
        return userRepository.findByUserEmailId(userEmailId).get();
    }
}
