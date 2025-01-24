package com.tekarch.TafDataStoreMS.services;

import com.tekarch.TafDataStoreMS.models.Bookings;

import java.util.List;

public interface BookingService {

    Bookings createBooking(Bookings booking);
    List<Bookings> getAllBookings();
    Bookings getBookingById(Long bookingId);
    List<Bookings> getBookingByuserId(Long userId);
    Bookings updateBooking(Long bookingId,Bookings updatedBooking);
    Bookings updateBookingDetails(Bookings booking);
    void deleteBooking(Long bookingId);

}
