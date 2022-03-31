package com.example.tasktracker.exception;

import com.example.tasktracker.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<ResponseDto<?>> handleError(IllegalArgumentException ex) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .timestamp(LocalDateTime.now())
                        .data(ex.toString())
                        .statusCode(BAD_REQUEST.value())
                        .status(BAD_REQUEST)
                        .message(ex.getMessage())
                        .build()
        );
    }


    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<ResponseDto<?>> handleError(NoSuchElementException ex) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .timestamp(LocalDateTime.now())
                        .data(ex.toString())
                        .statusCode(NOT_FOUND.value())
                        .status(NOT_FOUND)
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ResponseDto<?>> handleError(Exception ex) {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .timestamp(LocalDateTime.now())
                        .data(ex.toString())
                        .statusCode(INTERNAL_SERVER_ERROR.value())
                        .status(INTERNAL_SERVER_ERROR)
                        .message(ex.getMessage())
                        .build()
        );
    }


}
