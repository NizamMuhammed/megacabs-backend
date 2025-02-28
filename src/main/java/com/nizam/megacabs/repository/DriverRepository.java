package com.nizam.megacabs.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nizam.megacabs.model.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, ObjectId> {
    Optional<Driver> findByLicenseNumber(String licenseNumber);
}
