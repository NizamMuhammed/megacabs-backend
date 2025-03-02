package com.nizam.megacabs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.exception.EmailAlreadyExistsException;
import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder; // Remove @Autowired here

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(User user) {
        Optional<User> existingUserOptional = userRepository.findByUserEmailId(user.getUserEmailId());
        if (existingUserOptional.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String userEmailId) {
        Optional<User> userOptional = userRepository.findByUserEmailId(userEmailId);
        return userOptional.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserEmailId(userEmailId);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.withUsername(user.getUserEmailId())
                .password(user.getUserPassword())
                .roles(user.getRoles().stream().map(Enum::name).toArray(String[]::new))
                .build();
    }
}
