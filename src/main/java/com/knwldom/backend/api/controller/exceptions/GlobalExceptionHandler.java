package com.knwldom.backend.api.controller.exceptions;

import com.knwldom.backend.api.controller.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler {

    @ExceptionHandler(Api500Exception.class)
    public ResponseEntity<ErrorDto> handleCustomException(Api500Exception e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setError("Internal Server Error");
        errorDto.setMessage(e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}