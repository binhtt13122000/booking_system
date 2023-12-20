package com.demo.booking.mapper;

import com.demo.crud.models.Booking;
import com.demo.grpc.proto.BookingMessage;
import com.demo.grpc.proto.BookingReply;
import com.demo.grpc.proto.BookingStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static Booking convertToBooking(BookingMessage bookingMessage) {
        return Booking
                .builder()
                .id(bookingMessage.getBookingId())
                .checkoutTime(LocalDateTime.parse(bookingMessage.getCheckoutTime(), formatter))
                .checkinTime(LocalDateTime.parse(bookingMessage.getCheckinTime(), formatter))
                .guestFirstName(bookingMessage.getGuestFirstname())
                .guestId(bookingMessage.getGuestId())
                .guestLastName(bookingMessage.getGuestLastname())
                .roomId(bookingMessage.getRoomId())
                .active(bookingMessage.getActive())
                .build();
    }

    public static BookingReply convertToBookingReply(Booking booking, boolean success) {
        return BookingReply
                .newBuilder()
                .setBookingId(booking.getId())
                .setCheckoutTime(booking.getCheckoutTime().format(formatter))
                .setCheckinTime(booking.getCheckinTime().format(formatter))
                .setGuestFirstname(booking.getGuestFirstName())
                .setGuestId(booking.getGuestId())
                .setGuestLastname(booking.getGuestLastName())
                .setRoomId(booking.getRoomId())
                .setRoomName(booking.getRoomName())
                .setActive(booking.getActive())
                .setStatus(success ? BookingStatus.SUCCESS : BookingStatus.ERROR)
                .build();
    }
}
