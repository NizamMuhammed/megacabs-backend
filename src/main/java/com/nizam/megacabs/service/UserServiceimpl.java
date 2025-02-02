package com.nizam.megacabs.service;

import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl {
    @Autowired
    private UserRepository userRepository;

    public User signUp(User user){
        return userRepository.save(user);
    }

    public boolean signIn(String userEmailId,String userPassword){

        boolean flag=false;

        User user= userRepository.findByUserEmailIdAndUserPassword(userEmailId,userPassword);
        if(user!=null && user.getUserEmailId().equals(userEmailId) && user.getUserPassword().equals(userPassword)){
            flag=true;
        }
        return flag;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(); // Retrieves all users from MongoDB

    }

    public boolean deleteUser(String userId) {
        if (userRepository.existsById(Integer.valueOf(userId))) { // Using String as the type for userId
            userRepository.deleteById(Integer.valueOf(userId));
            return true;
        } else {
            return false;
        }
    }
}

