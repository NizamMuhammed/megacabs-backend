package com.nizam.megacabs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signUp(User user) {
        // Check if the email already exists in the database
        if (userRepository.existsByUserEmailId(user.getUserEmailId())) {
            // Log an error and throw the custom exception
            log.error("Email already exists: {}", user.getUserEmailId());
            throw new EmailAlreadyExistsException(user.getUserEmailId()+" Email already exists." );
        }

        // Save the new user to the database
        return userRepository.save(user);
    }

    @Override
    public boolean signIn(User user) {
        User existingUser = userRepository.findByUserEmailId(user.getUserEmailId());
        return existingUser != null && existingUser.getUserPassword().equals(user.getUserPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserEmailId(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserEmailId(), user.getUserPassword(), new ArrayList<>());
    }
}
