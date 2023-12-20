package com.demo.gateway.dto;

import com.demo.grpc.proto.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingResponseDto {
    private Integer bookingId;
    private Integer roomId;
    private String roomName;
    private Integer guestId;
    private String guestLastName;
    private String guestFirstName;
    private String checkinTime;
    private String checkoutTime;
    private String createdAt;
    private String updateAt;
    private Boolean active;
    private BookingStatus status;
}
