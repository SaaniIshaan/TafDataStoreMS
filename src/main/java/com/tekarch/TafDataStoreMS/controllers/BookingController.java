package com.tekarch.TafDataStoreMS.controllers;

import com.tekarch.TafDataStoreMS.models.Bookings;
import com.tekarch.TafDataStoreMS.services.BookingServiceImpl;
import com.tekarch.TafDataStoreMS.services.FlightServiceImpl;
import com.tekarch.TafDataStoreMS.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/datastore/bookings")
@RestController
public class BookingController {

    private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    public BookingController(BookingServiceImpl bookingServiceImpl){
        this.bookingServiceImpl = bookingServiceImpl;
    }

    // Create a new booking
    @PostMapping
    public ResponseEntity<Bookings> createBooking(@RequestBody Bookings booking) {
        Bookings createdBooking = bookingServiceImpl.createBooking(booking);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    // Get all bookings
    @GetMapping
    public ResponseEntity<List<Bookings>> getAllBookings() {
        List<Bookings> bookings = bookingServiceImpl.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable("userId") Long userId) {
        List<Bookings> bookings = bookingServiceImpl.getBookingByuserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bookings found for user ID: " + userId);
        }
        return ResponseEntity.ok(bookings);
    }


    // Get booking by bookingId
    @GetMapping("/{bookingId}")
    public ResponseEntity<Bookings> getBookingById(@PathVariable Long bookingId) {
        Bookings booking = bookingServiceImpl.getBookingById(bookingId);
        return booking != null ? new ResponseEntity<>(booking, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update booking (e.g., change status or flight)
    @PutMapping("/{bookingId}")
    public ResponseEntity<Bookings> updateBooking(@PathVariable Long bookingId, @RequestBody Bookings updatedBooking) {
        Bookings updated = bookingServiceImpl.updateBooking(bookingId, updatedBooking);
        return updated != null ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cancel a booking (mark as 'Cancelled')
    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingServiceImpl.deleteBooking(bookingId);  // Mark as cancelled
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    public ResponseEntity<String> respondWithError(Exception e) {
        logger.error("Exception Occurred. Details : {}", e.getMessage());
        return new ResponseEntity<>("Exception Occurred. More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
