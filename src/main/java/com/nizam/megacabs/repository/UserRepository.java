package com.nizam.megacabs.repository;

import com.nizam.megacabs.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUserEmailId(String userEmailId);

    // Add method to check if the email exists
    boolean existsByUserEmailId(String userEmailId);  // This method checks if an email exists
}
