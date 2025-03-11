package com.nizam.megacabs.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.model.Driver;
import com.nizam.megacabs.model.User;
import com.nizam.megacabs.repository.DriverRepository;
import com.nizam.megacabs.repository.UserRepository;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Driver> allDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> singleDriver(String id) {
        return driverRepository.findById(id);
    }

    @Override
    public Driver createDriver(Driver driver) {
        driver.setDriverId(UUID.randomUUID().toString());
        // Find associated user and set it
        Optional<User> user = userRepository.findById(driver.getUser().getUserId());
        if (user.isPresent()) {
            driver.setUser(user.get());
            return driverRepository.save(driver);
        }
        throw new RuntimeException("Associated user not found");
    }

    @Override
    public Driver updateDriver(String id, Driver driver) {
        driver.setDriverId(id);
        // Ensure user reference is maintained
        Optional<Driver> existingDriver = driverRepository.findById(id);
        if (existingDriver.isPresent()) {
            driver.setUser(existingDriver.get().getUser());
            return driverRepository.save(driver);
        }
        throw new RuntimeException("Driver not found");
    }

    @Override
    public void deleteDriver(String id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            // Delete the driver record
            driverRepository.deleteById(id);
            // Delete associated user account
            userRepository.deleteById(driver.get().getUser().getUserId());
        }
    }

    @Override
    public List<Driver> getActiveDrivers() {
        return driverRepository.findByDriverStatus("AVAILABLE");
    }
}
