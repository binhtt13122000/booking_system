package com.demo.gateway.dto;

import com.demo.common.Common;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ResponseDto {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Common.DATE_TIME_FORMAT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeStamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;
    private Object response;

    public ResponseDto(HttpStatus status, Map<String, String> errors, Object response) {
        super();
        timeStamp = LocalDateTime.now();
        this.status = status;
        this.errors = errors;
        this.response = response;
    }

    public ResponseDto(HttpStatus status, String error, Object response) {
        super();
        timeStamp = LocalDateTime.now();
        this.status = status;
        this.response = response;
        this.message = error;
    }

    public ResponseDto(HttpStatus status, Object response) {
        super();
        timeStamp = LocalDateTime.now();
        this.status = status;
        this.response = response;
    }

    public ResponseDto(HttpStatus status, int code, String message) {
        super();
        this.status = status;
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.code = String.valueOf(code);
        this.response = null;
    }

    public static ResponseEntity<ResponseDto> toResponseEntity(Object response) {
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK, response));
    }

    public static ResponseEntity<ResponseDto> toResponseEntity(HttpStatus httpStatus, int code, String desc) {
        return ResponseEntity.status(httpStatus).body(new ResponseDto(httpStatus, code, desc));
    }
}
