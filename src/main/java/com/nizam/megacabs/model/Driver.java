package com.nizam.megacabs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "drivers")
public class Driver {
    @Id
    private String driverId;
    private String driverName;
    private String licenseNumber;
    private String driverEmailId;
    private String driverPhone;
    private String driverStatus;  // AVAILABLE, UNAVAILABLE, ON_TRIP
    private String userId;        // Reference to User document
}
