package com.nizam.megacabs.service;

import com.nizam.megacabs.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User signUp(User user);
    boolean signIn(User user);
    UserDetails loadUserByUsername(String username);
}
