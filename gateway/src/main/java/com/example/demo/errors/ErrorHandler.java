package com.example.demo.errors;

import com.example.demo.errors.dto.ErrorDto;
import com.example.demo.errors.exceptions.UnAuthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = UnAuthorizedAccessException.class)
    public ResponseEntity<ErrorDto> unauthorizedAccess(UnAuthorizedAccessException e, ServerHttpRequest request){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ErrorDto.builder()
                        .message(e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.UNAUTHORIZED)
                        .path(request.getURI().getPath())
                        .build()
        );
    }
}
