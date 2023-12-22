package com.demo.identity.services;

import com.demo.common.JSONUtils;
import com.demo.grpc.proto.*;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
@Slf4j
public class IdentityGrpcServer extends UserServiceGrpc.UserServiceImplBase {
    @Autowired
    private UserService userService;

    @Override
    public void userSend(UserMessageRequest request, StreamObserver<UserMessageResponse> responseObserver) {
        try {
            log.info("Get message: {}", JSONUtils.getRawDataFromGrpc(request));
            UserMessageResponse userMessageResponse = null;
            switch (request.getType()) {
                case LOGIN_ACTION:
                    userMessageResponse = userService.login(request.getEmail(), request.getPassword());
                    break;
                case REGISTER_ACTION:
                    userMessageResponse = userService.register(request.getEmail(), request.getPassword());
                    break;
                case CHECK_EMAIL:
                    userMessageResponse = userService.findByEmail(request.getEmail());
                    break;
                default:
                    break;
            }

            log.info("Response: {}", JSONUtils.getRawDataFromGrpc(userMessageResponse));
            responseObserver.onNext(userMessageResponse);
            responseObserver.onCompleted();
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
