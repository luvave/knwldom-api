package com.knwldom.backend.api.controller.dto;

import lombok.Data;

@Data
public class RelationDto {
    private RelationTypeDto relationType;
    private String relationUri;
    private String from;
    private String to;
}
