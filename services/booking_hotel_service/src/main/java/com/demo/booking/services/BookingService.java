package com.demo.booking.services;

import com.demo.crud.booking.models.Booking;
import com.demo.grpc.proto.BookingMessage;
import com.demo.grpc.proto.BookingReply;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BookingService {
    boolean isAvailable(Integer id, LocalDate checkoutTime, LocalDate checkinTime, long bookingId);
    BookingReply createBooking(BookingMessage bookingMessage);
    BookingReply cancelBooking(Integer id);
    BookingReply updateBooking(BookingMessage bookingMessage);
    BookingReply findById(Integer id);
    List<Booking> findBookings(int page, int size, boolean isActive);
}
