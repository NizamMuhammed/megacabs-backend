package com.nizam.megacabs.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nizam.megacabs.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByUserId(String userId);
    List<Booking> findByDriverId(String driverId);
    List<Booking> findTop5ByOrderByDateDesc();
    List<Booking> findByDate(String date);
    List<Booking> findByDateBetween(String startDate, String endDate);
}
