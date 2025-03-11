package com.nizam.megacabs.service;

import java.util.List;
import java.util.Optional;

import com.nizam.megacabs.model.Driver;

public interface DriverService {
    List<Driver> allDrivers();
    Optional<Driver> singleDriver(String id);
    Driver createDriver(Driver driver);
    Driver updateDriver(String id, Driver driver);
    void deleteDriver(String id);
    List<Driver> getActiveDrivers();
}
