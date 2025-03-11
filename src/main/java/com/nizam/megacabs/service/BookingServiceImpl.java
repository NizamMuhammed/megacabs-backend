package com.nizam.megacabs.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nizam.megacabs.model.Booking;
import com.nizam.megacabs.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking assignDriver(String bookingId, String driverId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setDriverId(driverId);
        booking.setStatus("ASSIGNED");
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBookingStatus(String bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDriver(String driverId) {
        return bookingRepository.findByDriverId(driverId);
    }

    @Override
    public long count() {
        return bookingRepository.count();
    }

    @Override
    public List<Booking> getRecentBookings() {
        return bookingRepository.findTop5ByOrderByDateDesc();
    }

    @Override
    public Map<String, Double> getRevenue() {
        LocalDate today = LocalDate.now();
        List<Booking> dailyBookings = bookingRepository.findByDate(today.toString());
        List<Booking> monthlyBookings = bookingRepository.findByDateBetween(
            today.withDayOfMonth(1).toString(),
            today.toString()
        );

        double dailyRevenue = dailyBookings.stream()
            .mapToDouble(Booking::getPrice)
            .sum();
        
        double monthlyRevenue = monthlyBookings.stream()
            .mapToDouble(Booking::getPrice)
            .sum();

        Map<String, Double> revenue = new HashMap<>();
        revenue.put("daily", dailyRevenue);
        revenue.put("monthly", monthlyRevenue);
        
        return revenue;
    }
}
