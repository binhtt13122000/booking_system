package com.demo.gateway.services;

import com.demo.gateway.dto.BookingResponseDto;
import com.demo.gateway.dto.SearchResponseDto;
import com.demo.grpc.proto.BookingMessage;
import com.demo.grpc.proto.SearchMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;

public interface BookingService {
    BookingResponseDto proceed(BookingMessage request) throws InvalidProtocolBufferException, JsonProcessingException;

    SearchResponseDto search(SearchMessageRequest searchMessageRequest) throws InvalidProtocolBufferException, JsonProcessingException;
}
