package com.tekarch.TafDataStoreMS.services;

import com.tekarch.TafDataStoreMS.models.Bookings;
import com.tekarch.TafDataStoreMS.repositories.BookingRepository;
import com.tekarch.TafDataStoreMS.repositories.FlightRepository;
import com.tekarch.TafDataStoreMS.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository, UserRepository userRepository){
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bookings createBooking(Bookings booking) {
        return bookingRepository.save(booking);
    }


    @Override
    public List<Bookings> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Bookings getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public List<Bookings> getBookingByuserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Bookings updateBooking(Long bookingId, Bookings updatedBooking) {
        Bookings existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));

        // Update booking details (except ID and createdAt fields)
        existingBooking.setFlightId(updatedBooking.getFlightId());
        existingBooking.setUserId(updatedBooking.getUserId());
        existingBooking.setStatus(updatedBooking.getStatus());
        return bookingRepository.save(existingBooking);
    }

    @Override
    public Bookings updateBookingDetails(Bookings booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        // Mark the booking as cancelled
        Bookings booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));
        booking.setStatus("Cancelled");
        bookingRepository.save(booking);
    }
}
