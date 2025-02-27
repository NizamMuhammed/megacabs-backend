package com.nizam.megacabs.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    private String bookingId;
    private String pickupLocation;
    private String dropLocation;
    private LocalDate date;
    private LocalTime time;
    private String cabType;
    private double price;
    private String userId;//add this filed
}
