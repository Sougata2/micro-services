package com.example.gateway.errors.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private String path;
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
}
