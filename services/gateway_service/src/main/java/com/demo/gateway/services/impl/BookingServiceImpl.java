package com.demo.gateway.services.impl;

import com.demo.common.JSONUtils;
import com.demo.gateway.dto.BookingResponseDto;
import com.demo.gateway.dto.SearchResponseDto;
import com.demo.gateway.services.BookingService;
import com.demo.grpc.proto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {
    @Autowired
    @Qualifier("BookingChannel")
    private ManagedChannel channel;
    @Override
    public BookingResponseDto proceed(BookingMessage request) throws InvalidProtocolBufferException, JsonProcessingException {
        var stub = BookingServiceGrpc.newBlockingStub(channel);

        log.info("Send message: {} with type = {}", JSONUtils.getRawDataFromGrpc(request), request.getType());
        BookingReply bookingReply = stub.send(request);
        log.info("Getting response message: {}", JSONUtils.getRawDataFromGrpc(bookingReply));
        return JSONUtils.toObject(bookingReply, BookingResponseDto.class);
    }

    @Override
    public SearchResponseDto search(SearchMessageRequest searchMessageRequest) throws InvalidProtocolBufferException, JsonProcessingException {
        var stub = SearchServiceGrpc.newBlockingStub(channel);
        log.info("Send message: {}", JSONUtils.getRawDataFromGrpc(searchMessageRequest));
        SearchMessageResponse searchMessageResponse = stub.search(searchMessageRequest);
        log.info("Getting response message: {}", JSONUtils.getRawDataFromGrpc(searchMessageResponse));
        return JSONUtils.toObject(searchMessageResponse, SearchResponseDto.class);
    }
}
