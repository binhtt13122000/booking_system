package com.demo.gateway.exceptions;

import com.demo.gateway.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<ResponseDto> handleAuthenticationException(Exception ex) {

        ResponseDto re = new ResponseDto(HttpStatus.UNAUTHORIZED,
                "Authentication failed at controller advice", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ex.getBindingResult().getGlobalErrors().forEach((ObjectError error) -> {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        });

        ResponseDto apiError = new ResponseDto(status, errors, null);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }
}
