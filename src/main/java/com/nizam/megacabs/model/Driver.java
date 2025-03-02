package com.nizam.megacabs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "drivers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    private String driverId;
    private String driverName;
    private String licenseNumber;
    private String driverEmailId;
    private String driverPhone;
    private String driverStatus;
}
