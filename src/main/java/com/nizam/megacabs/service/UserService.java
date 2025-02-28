package com.nizam.megacabs.service;


import com.nizam.megacabs.dto.AuthResponse;
import com.nizam.megacabs.model.User;

public interface UserService {
    AuthResponse login(String userEmailId, String userPassword);
    User signUp(User user);
    //boolean signIn(User user); // removed this line
}
