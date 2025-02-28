package com.nizam.megacabs.controller;

import com.nizam.megacabs.exception.DriverAlreadyExistsException;
import com.nizam.megacabs.model.Driver;
import com.nizam.megacabs.service.DriverService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return new ResponseEntity<List<Driver>>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Driver>> getDriverById(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Driver>>(driverService.getDriverById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        try{
            return new ResponseEntity<Driver>(driverService.addDriver(driver), HttpStatus.CREATED);
        }catch(DriverAlreadyExistsException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) {
        return new ResponseEntity<Driver>(driverService.updateDriver(driver), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable ObjectId id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<String>("Driver deleted successfully",HttpStatus.OK);
    }
}
