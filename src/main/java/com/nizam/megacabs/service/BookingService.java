package com.nizam.megacabs.service;

import java.util.List;

import com.nizam.megacabs.model.Booking;

public interface BookingService {
    public Booking addBooking(Booking booking);
    //remove this line : public List<Booking> getAllBookings();
    public void deleteBooking(String id);
    List<Booking> getBookingsByUser(String userId); //added this method
}
