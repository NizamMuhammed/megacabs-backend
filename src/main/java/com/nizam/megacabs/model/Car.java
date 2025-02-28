package com.nizam.megacabs.model;

import java.util.List;

import org.bson.types.ObjectId;
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
    private ObjectId carId; // Changed field name to carId
    private String carName; // Changed field name to carName
    private String carType; // Changed field name to carType
    private String carNumber; // Changed field name to carNumber
    private String carStatus; // Changed cabStatus to string to represent one status
    private String carLocation; // Changed cabLocation to string to represent one location
    private String carCapacity; // Changed field name to carCapacity
    private String carDescription; // Changed field name to carDescription
    private String carImage; // Changed field name to carImage
    private String carBrand; // Changed field name to carBrand
    @DocumentReference
    private List<Review> reviewIds;
    private Boolean isAvailable;
}
