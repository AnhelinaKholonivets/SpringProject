package com.springtestproject.controller;

import com.springtestproject.dto.ApiExceptionDto;
import com.springtestproject.exception.LowBalanceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LowBalanceException.class)
    protected ResponseEntity<ApiExceptionDto> handleConflict() {
        String bodyOfResponse = "low balance";

        return ResponseEntity
                .status(403)
                .body(new ApiExceptionDto(bodyOfResponse, LocalDateTime.now()));

    }
}
