package com.nizam.megacabs.controller;

import com.nizam.megacabs.exception.CarAlreadyExistsException;
import com.nizam.megacabs.model.Car;
import com.nizam.megacabs.service.CarService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<List<Car>>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Car>>(carService.getCarById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        try{
            return new ResponseEntity<Car>(carService.addCar(car), HttpStatus.CREATED);
        } catch(CarAlreadyExistsException e){
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        return new ResponseEntity<Car>(carService.updateCar(car), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable ObjectId id) {
        carService.deleteCar(id);
        return new ResponseEntity<String>("Car deleted successfully",HttpStatus.OK);
    }
}
