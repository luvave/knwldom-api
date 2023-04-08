package com.knwldom.backend.api.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String name;
    private int id;
    private List<String> hasConnectionTo;
}
