package com.nizam.megacabs.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookings")
public class Booking {
    @Id
    private String bookingId;
    private String pickupLocation;
    private String dropLocation;
    private String date;
    private String time;
    private String cabType; // Added this field
    private int price;
    private String userId;
}
