package com.demo.identity.services.impl;

import com.demo.common.ErrorMessages;
import com.demo.crud.identity.models.User;
import com.demo.crud.identity.repositories.UserRepository;
import com.demo.grpc.proto.Status;
import com.demo.grpc.proto.UserMessageResponse;
import com.demo.identity.services.JWTService;
import com.demo.identity.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;
    @Override
    public UserMessageResponse login(String username, String password) {
        User user = userRepository.findByEmail(username);

        if(user == null) {
            return UserMessageResponse.newBuilder()
                    .setStatus(Status.ERROR)
                    .setCode(ErrorMessages.EMAIL_IS_NOT_REGISTERED.code)
                    .setDesc(ErrorMessages.EMAIL_IS_NOT_REGISTERED.desc)
                    .build();
        }

        if(passwordEncoder.matches(password, user.getPassword())) {
            return UserMessageResponse.newBuilder()
                    .setStatus(Status.SUCCESS)
                    .setCode(ErrorMessages.SUCCESS.code)
                    .setDesc(ErrorMessages.SUCCESS.desc)
                    .setToken(jwtService.generateToken(username))
                    .build();
        }

        return UserMessageResponse.newBuilder()
                .setStatus(Status.ERROR)
                .setCode(ErrorMessages.PASS_WRONG.code)
                .setDesc(ErrorMessages.PASS_WRONG.desc)
                .build();
    }

    @Override
    public UserMessageResponse register(String username, String password) {
        try {
            User user = new User();
            user.setEmail(username);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return UserMessageResponse
                    .newBuilder()
                    .setStatus(Status.SUCCESS)
                    .setCode(ErrorMessages.SUCCESS.code)
                    .setDesc(ErrorMessages.SUCCESS.desc)
                    .build();
        } catch (DataIntegrityViolationException ex) {
            log.error("Email = {} is available", username);
            return UserMessageResponse.newBuilder()
                    .setStatus(Status.ERROR)
                    .setCode(ErrorMessages.EMAIL_IS_REGISTERED.code)
                    .setDesc(ErrorMessages.EMAIL_IS_REGISTERED.desc)
                    .build();
        }
    }

    @Override
    public UserMessageResponse findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return UserMessageResponse.newBuilder()
                    .setStatus(Status.ERROR)
                    .setCode(ErrorMessages.EMAIL_IS_NOT_REGISTERED.code)
                    .setDesc(ErrorMessages.EMAIL_IS_NOT_REGISTERED.desc)
                    .build();
        }
        return UserMessageResponse.newBuilder()
                .setStatus(Status.SUCCESS)
                .setCode(ErrorMessages.SUCCESS.code)
                .setDesc(ErrorMessages.SUCCESS.desc)
                .build();
    }
}
