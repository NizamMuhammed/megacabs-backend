package com.nizam.megacabs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@Document( collection = "cabs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cabs {
    @Id
    private ObjectId cabId;
    private String cabName;
    private String cabType;
    private String cabNumber;
    private List<String> cabStatus;
    private List<String> cabLocation;
    private String cabCapacity;
    private String cabDescription;
    private String cabImage;
    private String cabBrand;
    @DocumentReference
    private List<Review> reviewIds;

}
