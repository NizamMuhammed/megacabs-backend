package com.nizam.megacabs.service;

import com.nizam.megacabs.dto.AuthResponse;
import com.nizam.megacabs.exception.EmailAlreadyExistsException; // Add this import
import com.nizam.megacabs.model.User;

public interface UserService {
    AuthResponse login(String userEmailId, String userPassword);
    User signUp(User user) throws EmailAlreadyExistsException; // Use the exception
    User findByEmail(String userEmailId);
}
