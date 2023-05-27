package com.knwldom.backend.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KnowledgeGraphDto {
    private String graphName;
    private String graphUri;
    private KnowledgeGraphTypeDto knowledgeGraphType;
    private List<RelationDto> relations;
    private List<KnowledgeGraphDto> contains;
}
