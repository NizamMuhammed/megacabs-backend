package com.nizam.megacabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nizam.megacabs.model.Booking;
import com.nizam.megacabs.model.User;
import com.nizam.megacabs.service.BookingService;
import com.nizam.megacabs.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking, Authentication authentication) {
        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the user's email (assuming you're using email as the principal)
            String userEmailId = authentication.getName(); //this is the email
            User user = userService.findByEmail(userEmailId); // get the user information with the email
            //set the user id in the booking object
            booking.setUserId(user.getUserId()); //set the userId and not the userEmailId
            //save the booking with user id
            Booking savedBooking = bookingService.addBooking(booking);
            return ResponseEntity.ok(savedBooking);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getUserBookings(Authentication authentication) { // Pass Authentication as a parameter
        if (authentication != null && authentication.isAuthenticated()) {
            String userEmailId = authentication.getName();
            User user = userService.findByEmail(userEmailId);
            List<Booking> userBookings = bookingService.getBookingsByUser(user.getUserId());
            return ResponseEntity.ok(userBookings);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
    }
}
