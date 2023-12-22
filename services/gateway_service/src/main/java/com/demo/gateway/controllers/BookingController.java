package com.demo.gateway.controllers;

import com.demo.common.Common;
import com.demo.gateway.dto.BookingResponseDto;
import com.demo.gateway.dto.CreateBookingDto;
import com.demo.gateway.dto.SearchResponseDto;
import com.demo.gateway.dto.UpdateBookingDto;
import com.demo.gateway.dto.ResponseDto;
import com.demo.gateway.services.BookingService;
import com.demo.grpc.proto.BookingMessage;
import com.demo.grpc.proto.Status;
import com.demo.grpc.proto.BookingType;
import com.demo.grpc.proto.SearchMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Secured({Common.USER})
    @GetMapping("/v1/bookings")
    public ResponseEntity<ResponseDto> getBookings(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "is_active", defaultValue = "false") boolean isActive) throws InvalidProtocolBufferException, JsonProcessingException {
        SearchMessageRequest searchMessageRequest = SearchMessageRequest
                .newBuilder()
                .setPage(page < 1 ? 0 : page - 1)
                .setSize(size)
                .setIsActive(isActive)
                .build();
        SearchResponseDto searchResponseDto = bookingService.search(searchMessageRequest);
        return ResponseDto.toResponseEntity(searchResponseDto);
    }

    @Secured({Common.USER})
    @PostMapping("/v1/booking")
    public ResponseEntity<ResponseDto> createBooking(@RequestBody @Valid CreateBookingDto request) throws InvalidProtocolBufferException, JsonProcessingException {
        BookingMessage bookingMessage = BookingMessage
                .newBuilder()
                .setRoomId(request.getRoomId())
                .setGuestId(request.getGuestId())
                .setCheckinTime(request.getCheckinTime())
                .setCheckoutTime(request.getCheckoutTime())
                .setGuestFirstname(request.getGuestFirstName())
                .setGuestLastname(request.getGuestLastName())
                .setType(BookingType.ADD)
                .build();
        var createResponse = bookingService.proceed(bookingMessage);
        
        if(createResponse.getStatus().equals(Status.SUCCESS)) {
            return ResponseDto.toResponseEntity(createResponse);
        }
        return ResponseDto.toResponseEntity(HttpStatus.BAD_REQUEST, createResponse.getCode(), createResponse.getDesc());
    }

    @Secured({Common.USER})
    @GetMapping("/v1/booking/{id}")
    public ResponseEntity<ResponseDto> getBooking(@PathVariable int id) throws InvalidProtocolBufferException, JsonProcessingException {
        BookingMessage bookingMessage = BookingMessage
                .newBuilder()
                .setBookingId(id)
                .setType(BookingType.VIEW_BY_ID)
                .build();
        BookingResponseDto bookingResponseDto = bookingService.proceed(bookingMessage);
        
        if(bookingResponseDto.getStatus().equals(Status.SUCCESS)) {
            return ResponseDto.toResponseEntity(bookingResponseDto);
        }
        return ResponseDto.toResponseEntity(HttpStatus.NOT_FOUND, bookingResponseDto.getCode(), bookingResponseDto.getDesc());
    }

    @Secured({Common.USER})
    @PutMapping("/v1/booking")
    public ResponseEntity<ResponseDto> updateBooking(@RequestBody @Valid UpdateBookingDto request) throws InvalidProtocolBufferException, JsonProcessingException {
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

        if(updateBookingResponseDto.getStatus().equals(Status.SUCCESS)) {
            return ResponseDto.toResponseEntity(updateBookingResponseDto);
        }
        return ResponseDto.toResponseEntity(HttpStatus.BAD_GATEWAY, updateBookingResponseDto.getCode(), updateBookingResponseDto.getDesc());}

    @Secured({Common.USER})
    @PutMapping("/v1/booking/{id}")
    public ResponseEntity<ResponseDto> cancelBooking(@PathVariable int id) throws InvalidProtocolBufferException, JsonProcessingException {
        BookingMessage bookingMessage = BookingMessage
                .newBuilder()
                .setBookingId(id)
                .setType(BookingType.CANCEL)
                .build();
        BookingResponseDto cancelBookingResponseDto = bookingService.proceed(bookingMessage);

        if(cancelBookingResponseDto.getStatus().equals(Status.SUCCESS)) {
            return ResponseDto.toResponseEntity(cancelBookingResponseDto);
        }
        return ResponseDto.toResponseEntity(HttpStatus.BAD_REQUEST, cancelBookingResponseDto.getCode(), cancelBookingResponseDto.getDesc());
    }
}
