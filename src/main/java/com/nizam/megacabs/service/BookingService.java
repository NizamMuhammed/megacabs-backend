package com.nizam.megacabs.service;

import java.util.List;

import com.nizam.megacabs.model.Booking;

public interface BookingService {
    public Booking addBooking(Booking booking);
    List<Booking> getAllBookings();
    public void deleteBooking(String id);
    List<Booking> getBookingsByUser(String userId);
    List<Booking> getBookingsByDriver(String driverId);
    Booking assignDriver(String bookingId, String driverId);
    Booking updateBookingStatus(String bookingId, String status);
}
