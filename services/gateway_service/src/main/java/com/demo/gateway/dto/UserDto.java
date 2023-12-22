package com.demo.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
