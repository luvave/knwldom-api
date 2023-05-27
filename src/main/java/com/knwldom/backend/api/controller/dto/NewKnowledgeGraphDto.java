package com.knwldom.backend.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewKnowledgeGraphDto {
    private String userId;
    private String graphUri;
    private String graphName;
    @JsonProperty("isDefault")
    private boolean isDefault;
    private String graphTypeUri;
}
