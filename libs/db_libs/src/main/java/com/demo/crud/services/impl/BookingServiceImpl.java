package com.demo.crud.services.impl;

import com.demo.crud.models.Booking;
import com.demo.crud.models.Room;
import com.demo.crud.repositories.BookingRepository;
import com.demo.crud.repositories.RoomRepository;
import com.demo.crud.services.BookingService;
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
    public boolean isAvailable(Integer id, LocalDate checkoutTime, LocalDate checkinTime) {
        return bookingRepository.countAlreadyBooked(id, checkoutTime, checkinTime) == 0;
    }

    @Override
    public Booking createBooking(Booking booking) {
        Room room = roomRepository.findById(booking.getRoomId()).orElse(null);
        if(room == null) {
            log.error("Room not found with id = {}", booking.getRoomId());
            return null;
        }
        if(isAvailable(room.getId(), booking.getCheckoutTime().toLocalDate(), booking.getCheckinTime().toLocalDate())) {
            booking.setActive(true);
            booking.setRoomName(room.getName());
            booking.setCreatedAt(LocalDateTime.now());
            booking.setUpdateAt(LocalDateTime.now());
            Booking saveBooking = bookingRepository.save(booking);
            log.info("Booking is saved successfully with id = {}", saveBooking.getId());
            return saveBooking;
        }

        log.info("Room is booked with id = {}", room.getId());
        return null;
    }

    @Override
    public Booking cancelBooking(Integer id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setActive(false);
        booking.setUpdateAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        Booking existBooking = bookingRepository.findById(booking.getId()).orElse(null);

        if(existBooking == null) {
            log.error("Booking not found with id = {}", booking.getId());
            return null;
        }

        if(isAvailable(existBooking.getRoomId(), booking.getCheckoutTime().toLocalDate(), booking.getCheckinTime().toLocalDate())) {
            existBooking.setCheckinTime(booking.getCheckinTime());
            existBooking.setCheckoutTime(booking.getCheckoutTime());
            existBooking.setGuestFirstName(booking.getGuestFirstName());
            existBooking.setGuestLastName(booking.getGuestLastName());
            existBooking.setUpdateAt(LocalDateTime.now());
            log.info("Booking is updated successfully with id = {}", existBooking.getId());
            return bookingRepository.save(existBooking);
        }

        log.info("Room {} is booked in updated time", booking.getRoomName());
        return null;
    }

    @Override
    public Booking findById(Integer id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> findBookings(int page, int size, boolean isActive) {
        Pageable pageable = PageRequest.of(page, size);
        if (isActive) {
            return bookingRepository.findAllByActiveIs(true, pageable);
        }
        return bookingRepository.findAll(pageable).getContent();
    }

    @Override
    public boolean existsByIdAndGuestId(Integer bookingId, Integer guestId) {
        return bookingRepository.existByIdAndGuestId(bookingId, guestId) > 0;
    }
}
