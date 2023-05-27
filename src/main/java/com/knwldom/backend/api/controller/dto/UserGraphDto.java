package com.knwldom.backend.api.controller.dto;

import lombok.Data;

@Data
public class UserGraphDto {
    private KnowledgeGraphDto graph;
    private Boolean isDefault;
}