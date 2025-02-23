package com.nizam.megacabs.service;

import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.UserRepository;
import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
