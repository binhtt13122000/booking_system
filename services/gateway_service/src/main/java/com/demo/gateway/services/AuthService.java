package com.demo.gateway.services;

import com.demo.gateway.dto.AuthResponseDto;
import com.demo.grpc.proto.UserMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;

public interface AuthService {
    AuthResponseDto proceed(UserMessageRequest request) throws InvalidProtocolBufferException, JsonProcessingException;
}
