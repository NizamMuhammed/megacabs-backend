package com.nizam.megacabs.service;

import com.nizam.megacabs.exception.DriverAlreadyExistsException;
import com.nizam.megacabs.model.Driver;
import com.nizam.megacabs.repository.DriverRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(ObjectId id) {
        return driverRepository.findById(id);
    }

    public Driver addDriver(Driver driver) {
        Optional<Driver> driverOptional = driverRepository.findByLicenseNumber(driver.getLicenseNumber());
        if(driverOptional.isPresent()){
            throw new DriverAlreadyExistsException("Driver with this license number is already present");
        }
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(ObjectId id) {
        driverRepository.deleteById(id);
    }
}
