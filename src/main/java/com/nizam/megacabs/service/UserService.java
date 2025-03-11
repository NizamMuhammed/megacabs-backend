package com.nizam.megacabs.service;

import java.util.List;

import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import com.nizam.megacabs.model.User;

public interface UserService {
    User signUp(User user) throws EmailAlreadyExistsException;
    User findByEmail(String userEmailId);
    List<User> findAll();
    void deleteUser(String userId);
    long count();
    long countDrivers();
}
