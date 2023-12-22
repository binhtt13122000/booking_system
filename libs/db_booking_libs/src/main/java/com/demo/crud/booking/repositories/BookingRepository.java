package com.demo.crud.booking.repositories;

import com.demo.crud.booking.models.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query(value = "SELECT count(1)   " +
            "        FROM bookings   " +
            "        WHERE room_id = :roomId  " +
            "        AND checkin_time <= :checkoutTime  " +
            "        AND checkout_time >= :checkinTime  " +
            "        AND active = true", nativeQuery = true)
    int countAlreadyBooked(@Param("roomId") long roomId, @Param("checkoutTime") LocalDate checkoutTime, @Param("checkinTime") LocalDate checkinTime);

    @Query(value = "SELECT count(1)   " +
            "        FROM Bookings   " +
            "        WHERE id = :bookingId  " +
            "        AND guest_id = :guestId " +
            "        AND active = true  ", nativeQuery = true)
    int existByIdAndGuestId(@Param("bookingId") Integer bookingId, @Param("guestId") Integer guestId);

    List<Booking> findAllByActiveIs(boolean active, Pageable pageable);
}
