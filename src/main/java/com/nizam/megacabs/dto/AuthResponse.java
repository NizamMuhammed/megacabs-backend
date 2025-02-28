package com.nizam.megacabs.dto;
import lombok.Data;

@Data
public class AuthResponse {
    private String userName;
    private String jwt;
    private String userId;

    public AuthResponse(String userName, String jwt, String userId) { //added this constructor
        this.userName = userName;
        this.jwt = jwt;
        this.userId = userId;
    }
        public AuthResponse(){

        }

}
