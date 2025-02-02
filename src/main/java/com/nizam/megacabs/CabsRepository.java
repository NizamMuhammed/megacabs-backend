package com.nizam.megacabs;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabsRepository extends MongoRepository<Cabs, ObjectId> {

    Optional<Cabs> findCabsByCabNumber(String CabNumber);
}
