package com.nizam.megacabs.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

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

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/driver/{driverId}")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<List<Booking>> getDriverBookings(@PathVariable String driverId) {
        try {
            logger.info("Fetching bookings for driver: {}", driverId);
            List<Booking> driverBookings = bookingService.getBookingsByDriver(driverId);
            logger.info("Found {} bookings for driver {}", driverBookings.size(), driverId);
            return ResponseEntity.ok(driverBookings);
        } catch (Exception e) {
            logger.error("Error fetching bookings for driver {}: {}", driverId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{bookingId}/assign-driver")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Booking> assignDriver(
            @PathVariable String bookingId,
            @RequestBody Map<String, String> payload) {
        String driverId = payload.get("driverId");
        Booking updatedBooking = bookingService.assignDriver(bookingId, driverId);
        return ResponseEntity.ok(updatedBooking);
    }

    @PutMapping("/{bookingId}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")  // Allow both ADMIN and DRIVER
    public ResponseEntity<Booking> updateBookingStatus(
            @PathVariable String bookingId,
            @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        Booking updatedBooking = bookingService.updateBookingStatus(bookingId, status);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> getBookingCount() {
        long count = bookingService.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/recent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Booking>> getRecentBookings() {
        List<Booking> recentBookings = bookingService.getRecentBookings();
        return ResponseEntity.ok(recentBookings);
    }

    @GetMapping("/revenue")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Double>> getRevenue() {
        Map<String, Double> revenue = bookingService.getRevenue();
        return ResponseEntity.ok(revenue);
    }
}
