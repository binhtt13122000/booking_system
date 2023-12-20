package com.demo.gateway.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponseDto {
    private List<BookingResponseDto> bookings;
    private int page;
    private int size;
}
