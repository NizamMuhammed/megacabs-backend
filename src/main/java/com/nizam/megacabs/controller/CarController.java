package com.nizam.megacabs.controller;

import com.nizam.megacabs.exception.CarAlreadyExistsException;
import com.nizam.megacabs.model.Car;
import com.nizam.megacabs.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<Page<Car>> getAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "carName") String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
        return new ResponseEntity<>(carService.getAllCars(pageRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable String id) {
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
    public ResponseEntity<String> deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
        return new ResponseEntity<String>("Car deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> getCarCount() {
        long count = carService.count();
        return ResponseEntity.ok(count);
    }
}
