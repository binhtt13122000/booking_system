package com.demo.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateBookingDto {
    @NotNull
    private Integer bookingId;
    @NotNull
    private String guestLastName;
    @NotNull
    private String guestFirstName;
    @NotNull
    private String checkinTime;
    @NotNull
    private String checkoutTime;
}
