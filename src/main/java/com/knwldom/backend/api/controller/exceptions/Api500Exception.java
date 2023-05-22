package com.knwldom.backend.api.controller.exceptions;

public class Api500Exception extends RuntimeException {
    public Api500Exception(String message) {
        super(message);
    }
}