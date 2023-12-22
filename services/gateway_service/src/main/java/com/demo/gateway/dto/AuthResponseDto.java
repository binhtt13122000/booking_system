package com.demo.gateway.dto;

import com.demo.grpc.proto.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDto {
    private String token;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean active;
    private Status status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int code;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String desc;
}
