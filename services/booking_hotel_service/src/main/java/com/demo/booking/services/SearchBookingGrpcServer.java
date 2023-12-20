package com.demo.booking.services;

import com.demo.booking.mapper.Mapper;
import com.demo.common.JSONUtils;
import com.demo.crud.models.Booking;
import com.demo.crud.services.BookingService;
import com.demo.grpc.proto.*;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@Slf4j
public class SearchBookingGrpcServer extends SearchServiceGrpc.SearchServiceImplBase {
    @Autowired
    private BookingService bookingService;

    @Override
    public void search(SearchMessageRequest request, StreamObserver<SearchMessageResponse> responseObserver) {
        try {
            log.info("Get message: {}", JSONUtils.getRawDataFromGrpc(request));
            List<Booking> bookings = bookingService.findBookings(request.getPage(), request.getSize(), request.getIsActive());
            SearchMessageResponse searchMessageResponse =
                    SearchMessageResponse
                            .newBuilder()
                            .setSize(request.getSize())
                            .setPage(request.getPage() + 1)
                            .addAllBookings(bookings.stream().map(booking -> Mapper.convertToBookingReply(booking, true)).collect(Collectors.toList()))
                            .build();
            log.info("Response: {}", JSONUtils.getRawDataFromGrpc(searchMessageResponse));
            responseObserver.onNext(searchMessageResponse);
            responseObserver.onCompleted();
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
