package com.nizam.megacabs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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
    private String licenseNumber;
    private String driverPhone;
    private String driverStatus;  // AVAILABLE, UNAVAILABLE, ON_TRIP
    @DocumentReference
    private User user;            // Reference to User document

    // Helper methods to access user information
    public String getDriverName() {
        return user != null ? user.getUserName() : null;
    }

    public String getDriverEmailId() {
        return user != null ? user.getUserEmailId() : null;
    }

    public String getUserId() {
        return user != null ? user.getUserId() : null;
    }
}
