package com.knwldom.backend.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/public")
public class PublicController {

    @GetMapping(value = "/")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.ok("Public Endpoint is working!");
    }
}
