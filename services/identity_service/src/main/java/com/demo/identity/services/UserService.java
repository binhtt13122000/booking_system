package com.demo.identity.services;

import com.demo.grpc.proto.UserMessageResponse;

public interface UserService {
    UserMessageResponse login(String username, String password);
    UserMessageResponse register(String username, String password);
    UserMessageResponse findByEmail(String email);
}
