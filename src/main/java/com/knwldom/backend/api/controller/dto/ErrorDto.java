package com.knwldom.backend.api.controller.dto;

import lombok.Data;

@Data
public class ErrorDto {
    private int status;
    private String error;
    private String message;
}