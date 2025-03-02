package com.nizam.megacabs.dto;

import java.util.List; // Import your Role enum

import com.nizam.megacabs.model.Role;

import lombok.Data;

@Data
public class AuthResponse {
    private String userName;
    private String jwt;
    private String userId;
    private List<Role> roles; // Add this field

    // Constructor including roles
    public AuthResponse(String userName, String jwt, String userId, List<Role> roles) {
        this.userName = userName;
        this.jwt = jwt;
        this.userId = userId;
        this.roles = roles;
    }
    // the previous constructor was not used but if needed
    public AuthResponse(String userName, String jwt, String userId) { 
        this.userName = userName;
        this.jwt = jwt;
        this.userId = userId;
      
    }

    // Getters and setters (you can use @Data annotation from Lombok instead)
   
}
