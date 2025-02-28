package com.nizam.megacabs.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.UserRepository;

@Service
public class ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserEmailId(userEmailId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(user.getUserEmailId(), user.getUserPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with userEmailId: " + userEmailId);
        }
    }
}
