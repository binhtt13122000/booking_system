package com.demo.gateway.services.impl;

import com.demo.common.JSONUtils;
import com.demo.gateway.dto.AuthResponseDto;
import com.demo.gateway.services.AuthService;
import com.demo.grpc.proto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    @Qualifier("AuthChannel")
    private ManagedChannel channel;
    @Override
    public AuthResponseDto proceed(UserMessageRequest request) throws InvalidProtocolBufferException, JsonProcessingException {
        var stub = UserServiceGrpc.newBlockingStub(channel);

        log.info("Send message: {} with type = {}", JSONUtils.getRawDataFromGrpc(request), request.getType());
        UserMessageResponse userMessageResponse = stub.userSend(request);
        log.info("Getting response message: {}", JSONUtils.getRawDataFromGrpc(userMessageResponse));
        return JSONUtils.toObject(userMessageResponse, AuthResponseDto.class);
    }
}
