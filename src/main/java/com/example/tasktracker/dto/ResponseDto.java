package com.example.tasktracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * DTO class for Response
 * @author Olzhas Syrbek
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ResponseDto<T> {
    private int statusCode;
    private HttpStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private T data;
}
