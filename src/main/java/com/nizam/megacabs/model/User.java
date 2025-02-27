package com.nizam.megacabs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data // This annotation provides getter and setter methods
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String userName;
    private String userEmailId;
    private String userPassword;
    //no need to add getter and setter because of the @Data annotation
}
