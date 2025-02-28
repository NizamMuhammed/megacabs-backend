package com.nizam.megacabs.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nizam.megacabs.model.Car;

@Repository
public interface CarRepository extends MongoRepository<Car, ObjectId> {
    Optional<Car> findByCarNumber(String carNumber);
}
