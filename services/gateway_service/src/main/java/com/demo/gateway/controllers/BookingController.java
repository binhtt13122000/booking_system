package com.demo.gateway.controllers;

import com.demo.gateway.dto.BookingResponseDto;
import com.demo.gateway.dto.CreateBookingDto;
import com.demo.gateway.dto.SearchResponseDto;
import com.demo.gateway.dto.UpdateBookingDto;
import com.demo.gateway.services.BookingService;
import com.demo.grpc.proto.BookingMessage;
import com.demo.grpc.proto.BookingStatus;
import com.demo.grpc.proto.BookingType;
import com.demo.grpc.proto.SearchMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/v1/guest/{id}/bookings")
    public ResponseEntity<SearchResponseDto> getBookings(
            @PathVariable int id,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "is_active", defaultValue = "false") boolean isActive) throws InvalidProtocolBufferException, JsonProcessingException {
        SearchMessageRequest searchMessageRequest = SearchMessageRequest
                .newBuilder()
                .setGuestId(id)
                .setPage(page < 1 ? 0 : page - 1)
                .setSize(size)
                .setIsActive(isActive)
                .build();
        SearchResponseDto searchResponseDto = bookingService.search(searchMessageRequest);
        return ResponseEntity.ok(searchResponseDto);
    }

    @PostMapping("/v1/booking")
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody @Valid CreateBookingDto request) throws InvalidProtocolBufferException, JsonProcessingException {
        BookingMessage bookingMessage = BookingMessage
                .newBuilder()
                .setRoomId(request.getRoomId())
                .setGuestId(request.getGuestId())
                .setCheckinTime(request.getCheckinTime())
                .setCheckoutTime(request.getCheckoutTime())
                .setType(BookingType.ADD)
                .build();
        var createResponse = bookingService.proceed(bookingMessage);
        
        if(createResponse.getStatus().equals(BookingStatus.SUCCESS)) {
            return ResponseEntity.ok(createResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/v1/booking/{id}")
    public ResponseEntity<BookingResponseDto> getBooking(@PathVariable int id) throws InvalidProtocolBufferException, JsonProcessingException {
        BookingMessage bookingMessage = BookingMessage
                .newBuilder()
                .setBookingId(id)
                .setType(BookingType.VIEW_BY_ID)
                .build();
        BookingResponseDto bookingResponseDto = bookingService.proceed(bookingMessage);
        
        if(bookingResponseDto.getStatus().equals(BookingStatus.SUCCESS)) {
            return ResponseEntity.ok(bookingResponseDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/v1/booking")
    public ResponseEntity<BookingResponseDto> updateBooking(@RequestBody @Valid UpdateBookingDto request) throws InvalidProtocolBufferException, JsonProcessingException {
        BookingMessage bookingMessage = BookingMessage
                .newBuilder()
                .setBookingId(request.getBookingId())
                .setGuestFirstname(request.getGuestFirstName())
                .setGuestLastname(request.getGuestLastName())
                .setCheckoutTime(request.getCheckoutTime())
                .setCheckinTime(request.getCheckinTime())
                .setType(BookingType.UPDATE)
                .build();
        BookingResponseDto updateBookingResponseDto = bookingService.proceed(bookingMessage);

        if(updateBookingResponseDto.getStatus().equals(BookingStatus.SUCCESS)) {
            return ResponseEntity.ok(updateBookingResponseDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/v1/booking/{id}")
    public ResponseEntity<BookingResponseDto> cancelBooking(@PathVariable int id) throws InvalidProtocolBufferException, JsonProcessingException {
        BookingMessage bookingMessage = BookingMessage
                .newBuilder()
                .setBookingId(id)
                .setType(BookingType.CANCEL)
                .build();
        BookingResponseDto cancelBookingResponseDto = bookingService.proceed(bookingMessage);

        if(cancelBookingResponseDto.getStatus().equals(BookingStatus.SUCCESS)) {
            return ResponseEntity.ok(cancelBookingResponseDto);
        }
        return ResponseEntity.badRequest().build();
    }
}
