package com.demo.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateBookingDto {
    @NotNull
    private Integer roomId;
    @NotNull
    private Integer guestId;
    @NotNull
    private String guestLastName;
    @NotNull
    private String guestFirstName;
    @NotNull
    private String checkinTime;
    @NotNull
    private String checkoutTime;
}
