package com.nizam.megacabs.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String userName;
    private String userEmailId;
    private String userPassword;
    private List<Role> roles = new ArrayList<>();
    public List<Role> getRoles(){
        return roles;
   }
   public void setRoles(List<Role> roles){
        this.roles = roles;
   }
   }
