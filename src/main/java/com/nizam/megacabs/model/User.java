package com.nizam.megacabs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    private String userId;  // Use String to handle MongoDB's auto-generation
    private String userName;
    private String userEmailId;
    private String userPassword;
    private String userRole;
}
