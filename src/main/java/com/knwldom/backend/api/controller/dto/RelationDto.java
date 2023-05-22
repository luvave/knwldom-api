package com.knwldom.backend.api.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class RelationDto {

    private String name;
    private List<String> to;
}
