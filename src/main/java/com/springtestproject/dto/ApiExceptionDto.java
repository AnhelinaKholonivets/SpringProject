package com.springtestproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiExceptionDto {
    private String message;
    private LocalDateTime exceptionTime;
}
