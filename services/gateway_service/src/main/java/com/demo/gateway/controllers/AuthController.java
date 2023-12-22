package com.demo.gateway.controllers;

import com.demo.gateway.dto.AuthResponseDto;
import com.demo.gateway.dto.ResponseDto;
import com.demo.gateway.dto.UserDto;
import com.demo.gateway.services.AuthService;
import com.demo.grpc.proto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/v1/register")
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid UserDto request) throws InvalidProtocolBufferException, JsonProcessingException {
        UserMessageRequest userMessageRequest = fromUserDto(request, ActionType.REGISTER_ACTION);
        var createResponse = authService.proceed(userMessageRequest);

        if(createResponse.getStatus().equals(Status.SUCCESS)) {
            return ResponseDto.toResponseEntity(createResponse);
        }
        return ResponseDto.toResponseEntity(HttpStatus.BAD_REQUEST, createResponse.getCode(), createResponse.getDesc());
    }

    @PostMapping("/v1/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid UserDto request) throws InvalidProtocolBufferException, JsonProcessingException {
        UserMessageRequest userMessageRequest = fromUserDto(request, ActionType.LOGIN_ACTION);
        var loginResponse = authService.proceed(userMessageRequest);

        return ResponseDto.toResponseEntity(loginResponse);
    }

    private UserMessageRequest fromUserDto(UserDto userDto, ActionType actionType) {
        return UserMessageRequest
                .newBuilder()
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword())
                .setType(actionType)
                .build();
    }
}
