package com.nizam.megacabs.repository;

import com.nizam.megacabs.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    User findByUserEmailIdAndUserPassword(String userEmailId, String userPassword);
}
