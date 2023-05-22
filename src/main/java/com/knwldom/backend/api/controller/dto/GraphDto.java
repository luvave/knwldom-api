package com.knwldom.backend.api.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class GraphDto {

    private String name;
    private String type;
    private List<GraphDto> nestedGraphs;

    private List<RelationDto> relations;
}
