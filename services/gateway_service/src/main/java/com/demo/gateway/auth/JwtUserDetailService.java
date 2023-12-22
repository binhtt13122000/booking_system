package com.demo.gateway.auth;

import com.demo.common.Common;
import com.demo.gateway.dto.AuthResponseDto;
import com.demo.gateway.services.AuthService;
import com.demo.grpc.proto.ActionType;
import com.demo.grpc.proto.Status;
import com.demo.grpc.proto.UserMessageRequest;
import com.demo.grpc.proto.UserMessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {
    @Autowired
    private AuthService authService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null){
            return null;
        }
        try {
            AuthResponseDto response = authService.proceed(UserMessageRequest.newBuilder()
                    .setEmail(username).setType(ActionType.CHECK_EMAIL).build());
            if(Status.SUCCESS.equals(response.getStatus())){
                return new User(username, "", Collections.singleton(new SimpleGrantedAuthority(Common.USER)));
            }
        } catch (InvalidProtocolBufferException | JsonProcessingException e) {
            return null;
        }

        return null;
    }
}