package com.demo.booking.services;

import com.demo.common.JSONUtils;
import com.demo.grpc.proto.BookingMessage;
import com.demo.grpc.proto.BookingReply;
import com.demo.grpc.proto.BookingServiceGrpc;
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
            BookingReply booking = null;
            switch (request.getType()) {
                case ADD:
                    booking = bookingService.createBooking(request);
                    break;
                case CANCEL:
                    booking = bookingService.cancelBooking(request.getBookingId());
                    break;
                case UPDATE:
                    booking = bookingService.updateBooking(request);
                    break;
                case VIEW_BY_ID:
                    booking = bookingService.findById(request.getBookingId());
                    break;
                default:
                    break;
            }

            log.info("Response: {}", JSONUtils.getRawDataFromGrpc(booking));
            responseObserver.onNext(booking);
            responseObserver.onCompleted();
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
