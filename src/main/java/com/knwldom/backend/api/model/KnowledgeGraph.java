package com.knwldom.backend.api.model;

import com.knwldom.backend.api.controller.dto.KnowledgeGraphDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeGraph {
    private KnowledgeGraphType knowledgeGraphType;
    private String graphUri;
    private String graphName;
    private List<Relation> relations;
    private List<KnowledgeGraph> contains;
}