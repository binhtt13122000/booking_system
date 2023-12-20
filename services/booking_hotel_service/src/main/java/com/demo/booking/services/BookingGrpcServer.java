package com.demo.booking.services;

import com.demo.booking.mapper.Mapper;
import com.demo.common.JSONUtils;
import com.demo.crud.models.Booking;
import com.demo.crud.services.BookingService;
import com.demo.grpc.proto.BookingMessage;
import com.demo.grpc.proto.BookingReply;
import com.demo.grpc.proto.BookingServiceGrpc;
import com.demo.grpc.proto.BookingStatus;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
@Slf4j
public class BookingGrpcServer extends BookingServiceGrpc.BookingServiceImplBase {
    @Autowired
    private BookingService bookingService;

    @Override
    public void send(BookingMessage request, StreamObserver<BookingReply> responseObserver) {
        try {
            log.info("Get message: {}", JSONUtils.getRawDataFromGrpc(request));
            Booking booking = null;
            switch (request.getType()) {
                case ADD -> booking = bookingService
                        .createBooking(Mapper.convertToBooking(request));
                case CANCEL -> booking = bookingService
                        .cancelBooking(request.getBookingId());
                case UPDATE -> booking = bookingService
                        .updateBooking(Mapper.convertToBooking(request));
                case VIEW_BY_ID -> booking = bookingService.findById(request.getBookingId());
            }

            BookingReply bookingReply;
            if (booking == null) {
                bookingReply = BookingReply.newBuilder().setStatus(BookingStatus.ERROR).setBookingId(-1).build();
            } else {
                bookingReply = Mapper.convertToBookingReply(booking, true);
            }

            log.info("Response: {}", JSONUtils.getRawDataFromGrpc(bookingReply));
            responseObserver.onNext(bookingReply);
            responseObserver.onCompleted();
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
