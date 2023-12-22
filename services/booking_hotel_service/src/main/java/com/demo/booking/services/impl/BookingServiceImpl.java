package com.demo.booking.services.impl;

import com.demo.booking.mapper.Mapper;
import com.demo.booking.services.BookingService;
import com.demo.common.ErrorMessages;
import com.demo.crud.booking.models.Booking;
import com.demo.crud.booking.models.Room;
import com.demo.crud.booking.repositories.BookingRepository;
import com.demo.crud.booking.repositories.RoomRepository;
import com.demo.grpc.proto.BookingMessage;
import com.demo.grpc.proto.BookingReply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public boolean isAvailable(Integer id, LocalDate checkoutTime, LocalDate checkinTime, long bookingId) {
        return bookingRepository.countAlreadyBooked(id, checkoutTime, checkinTime, bookingId) == 0;
    }

    @Override
    public BookingReply createBooking(BookingMessage bookingMessage) {
        Booking booking = Mapper.convertToBooking(bookingMessage);
        Room room = roomRepository.findById(booking.getRoomId()).orElse(null);
        if(room == null) {
            log.error("Room not found with id = {}", booking.getRoomId());
            return Mapper.convertToErrorBookingReply(ErrorMessages.ROOM_NOT_FOUND.code, ErrorMessages.ROOM_NOT_FOUND.desc);
        }
        if(isAvailable(room.getId(), booking.getCheckoutTime().toLocalDate(), booking.getCheckinTime().toLocalDate(), 0L)) {
            booking.setActive(true);
            booking.setRoomName(room.getName());
            booking.setCreatedAt(LocalDateTime.now());
            booking.setUpdateAt(LocalDateTime.now());
            Booking saveBooking = bookingRepository.save(booking);
            log.info("Booking is saved successfully with id = {}", saveBooking.getId());
            return Mapper.convertToBookingReply(saveBooking);
        }

        log.info("Room is booked with id = {}", room.getId());
        return Mapper.convertToErrorBookingReply(ErrorMessages.ROOM_NOT_AVAILABLE.code, ErrorMessages.ROOM_NOT_AVAILABLE.desc);
    }

    @Override
    public BookingReply cancelBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).orElse(null);

        if(booking == null) {
            log.error("Booking is not found with id = {}", id);
            return Mapper.convertToErrorBookingReply(ErrorMessages.BOOKING_NOT_FOUND.code, ErrorMessages.BOOKING_NOT_FOUND.desc);
        }

        if(!booking.getActive()) {
            log.error("Booking is already deactived with id = {}", id);
            return Mapper.convertToErrorBookingReply(ErrorMessages.BOOKING_ALREADY_DEACTIVE.code, ErrorMessages.BOOKING_ALREADY_DEACTIVE.desc);
        }
        booking.setActive(false);
        booking.setUpdateAt(LocalDateTime.now());
        return Mapper.convertToBookingReply(bookingRepository.save(booking));
    }

    @Override
    public BookingReply updateBooking(BookingMessage bookingMessage) {
        Booking booking = Mapper.convertToBooking(bookingMessage);
        Booking existBooking = bookingRepository.findById(booking.getId()).orElse(null);

        if(existBooking == null) {
            log.error("Booking not found with id = {}", booking.getId());
            return Mapper.convertToErrorBookingReply(ErrorMessages.BOOKING_NOT_FOUND.code, ErrorMessages.BOOKING_NOT_FOUND.desc);
        }

        if(isAvailable(existBooking.getRoomId(), booking.getCheckoutTime().toLocalDate(), booking.getCheckinTime().toLocalDate(), booking.getId())) {
            existBooking.setCheckinTime(booking.getCheckinTime());
            existBooking.setCheckoutTime(booking.getCheckoutTime());
            existBooking.setGuestFirstName(booking.getGuestFirstName());
            existBooking.setGuestLastName(booking.getGuestLastName());
            existBooking.setUpdateAt(LocalDateTime.now());
            log.info("Booking is updated successfully with id = {}", existBooking.getId());
            return Mapper.convertToBookingReply(bookingRepository.save(existBooking));
        }

        log.info("Room {} is booked in updated time", booking.getRoomName());
        return Mapper.convertToErrorBookingReply(ErrorMessages.ROOM_NOT_AVAILABLE.code, ErrorMessages.ROOM_NOT_AVAILABLE.desc);
    }

    @Override
    public BookingReply findById(Integer id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if(booking == null) {
            log.error("Booking not found with id = {}", id);
            return Mapper.convertToErrorBookingReply(ErrorMessages.BOOKING_NOT_FOUND.code, ErrorMessages.BOOKING_NOT_FOUND.desc);
        }

        return Mapper.convertToBookingReply(booking);
    }

    @Override
    public List<Booking> findBookings(int page, int size, boolean isActive) {
        Pageable pageable = PageRequest.of(page, size);
        if (isActive) {
            return bookingRepository.findAllByActiveIs(true, pageable);
        }
        return bookingRepository.findAll(pageable).getContent();
    }
}
