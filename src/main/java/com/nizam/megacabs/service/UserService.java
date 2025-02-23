package com.nizam.megacabs.service;

import com.nizam.megacabs.model.User;

public interface UserService {
    User signUp(User user);
    boolean signIn(User user);  // Modify this to accept a User object
}
