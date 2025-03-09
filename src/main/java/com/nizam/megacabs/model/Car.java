package com.nizam.megacabs.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nizam.megacabs.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@CrossOrigin(origins = "*")
@Document(collection = "cars") // Changed collection name to "cars"
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    private String carId; // Changed from ObjectId to String
    private String carName;
    private String carType;
    private String carNumber;
    private String carStatus;
    private String carLocation;
    private String carCapacity;
    private String carDescription;
    private String carImage;
    private String carBrand;
    @DocumentReference
    private List<Review> reviewIds;
    private Boolean isAvailable;
}
