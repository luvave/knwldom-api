package com.knwldom.backend.api.controller.dto;

import lombok.Data;

@Data
public class NewRelationDto {

    String graphUri;
    String relationName;
    String to;
}
