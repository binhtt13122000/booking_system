package com.demo.gateway.dto;

import com.demo.grpc.proto.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {
    private Integer bookingId;
    private Integer roomId;
    private String roomName;
    private Integer guestId;
    private String guestLastname;
    private String guestFirstname;
    private String checkinTime;
    private String checkoutTime;
    private String createdAt;
    private String updateAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean active;
    private Status status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int code;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String desc;
}
