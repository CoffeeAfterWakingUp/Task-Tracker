package com.example.tasktracker.rest;

import com.example.tasktracker.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.OK;

/**
 * Controller contains most used methods, for nonduplication
 *
 * @author Olzhas Syrbek
 */
public class AbstractRestController {

    public static final String SUCCESS_MSG = "success";

    protected <T> ResponseEntity<ResponseDto<T>> success(T data, HttpStatus status) {
        ResponseDto<T> responseDto = new ResponseDto<>(
                status.value(),
                status,
                LocalDateTime.now(),
                SUCCESS_MSG,
                data
        );
        return new ResponseEntity<>(responseDto, OK);
    }

    protected <T> ResponseEntity<ResponseDto<T>> success(T data) {
        return success(data, OK);
    }
}
