package com.nizam.megacabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String userName;
    private String message;
    // You can add other fields like a JWT token here if you use it
}
