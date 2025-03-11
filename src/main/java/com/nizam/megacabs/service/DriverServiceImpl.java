package com.nizam.megacabs.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.model.Driver;
import com.nizam.megacabs.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

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
        // generate random id
        driver.setDriverId(UUID.randomUUID().toString());
        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(String id, Driver driver) {
        // set the id for updating
        driver.setDriverId(id);
        return driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(String id) {
        driverRepository.deleteById(id);
    }

    @Override
    public List<Driver> getActiveDrivers() {
        return driverRepository.findByDriverStatus("AVAILABLE");
    }
}
