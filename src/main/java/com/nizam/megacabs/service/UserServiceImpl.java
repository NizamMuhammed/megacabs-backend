package com.nizam.megacabs.service;

import com.nizam.megacabs.dto.AuthResponse;
import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.UserRepository;
import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j // This will take care of the "log" error
public class UserServiceImpl implements UserService { // Implement the interface

    @Autowired
    private UserRepository userRepository;

    @Override // Now we are correctly overriding a method from the interface
    public User signUp(User user) {
        // Check if the email already exists in the database
        if (userRepository.existsByUserEmailId(user.getUserEmailId())) {
            // Log an error and throw the custom exception
            log.error("Email already exists: {}", user.getUserEmailId());
            throw new EmailAlreadyExistsException(user.getUserEmailId() + " Email already exists.");
        }

        // Save the new user to the database
        return userRepository.save(user);
    }

     @Override // Now we are correctly overriding a method from the interface
     public boolean signIn(User user) {
         Optional<User> optionalUser = userRepository.findByUserEmailId(user.getUserEmailId());
          if (optionalUser.isPresent()) {
           User existingUser = optionalUser.get();
              return existingUser.getUserPassword().equals(user.getUserPassword());
           }else{
              return false;
          }

     }
    @Override
     public AuthResponse login(String userEmailId, String userPassword) {
        Optional<User> optionalUser = userRepository.findByUserEmailId(userEmailId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getUserPassword().equals(userPassword)) {
                // Authentication successful
                AuthResponse response = new AuthResponse();
                response.setUserName(user.getUserName());
                response.setMessage("Login successful");
                return response;

            }
        }
        //Authentication Failed
        return null;
    }
}
