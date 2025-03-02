package com.nizam.megacabs.service;

import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import com.nizam.megacabs.model.User;

public interface UserService {
    //removed this method: AuthResponse login(String userEmailId, String userPassword);
    User signUp(User user) throws EmailAlreadyExistsException; 
    User findByEmail(String userEmailId);
}
