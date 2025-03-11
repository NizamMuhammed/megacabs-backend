package com.nizam.megacabs.service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired; // added import
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.exception.CarAlreadyExistsException;
import com.nizam.megacabs.model.Car;
import com.nizam.megacabs.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Cacheable("cars")
    public Page<Car> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    public Optional<Car> getCarById(String id) {
        // Convert String to ObjectId
        return carRepository.findById(new ObjectId(id));
    }

    public Car addCar(Car car) {
        Optional<Car> carOptional = carRepository.findByCarNumber(car.getCarNumber());
        if(carOptional.isPresent()){
            throw new CarAlreadyExistsException("Car with this number is already present");
        }
        return carRepository.save(car);
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(String id) {
        // Convert String to ObjectId
        carRepository.deleteById(new ObjectId(id));
    }

    public long count() {
        return carRepository.count();
    }
}
