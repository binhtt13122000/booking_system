package com.demo.crud.services;

import com.demo.crud.models.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    boolean isAvailable(Integer id, LocalDate checkoutTime, LocalDate checkinTime);
    Booking createBooking(Booking booking);
    Booking cancelBooking(Integer id);
    Booking updateBooking(Booking booking);
    Booking findById(Integer id);
    List<Booking> findBookings(int page, int size, boolean isActive);
    boolean existsByIdAndGuestId(Integer bookingId, Integer guestId);
}
