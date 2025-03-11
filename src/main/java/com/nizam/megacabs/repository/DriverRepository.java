package com.nizam.megacabs.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nizam.megacabs.model.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
    List<Driver> findByDriverStatus(String status);
}
