package com.demo.common;

public enum ErrorMessages {
    SUCCESS(0, "SUCCESS"),
    ROOM_NOT_FOUND(1, "ROOM_NOT_FOUND"),
    ROOM_NOT_AVAILABLE(2, "ROOM_NOT_AVAILABLE"),
    BOOKING_NOT_FOUND(3, "BOOKING_NOT_FOUND"),
    BOOKING_ALREADY_DEACTIVE(4, "BOOKING_ALREADY_DEACTIVE"),
    EMAIL_IS_REGISTERED(5, "EMAIL_IS_REGISTERED"),
    EMAIL_IS_NOT_REGISTERED(6, "EMAIL_IS_NOT_REGISTERED"),
    PASS_WRONG(7, "PASS_WRONG");


    public int code;
    public String desc;

    private ErrorMessages(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
