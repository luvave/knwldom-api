package com.knwldom.backend.api.model;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
public class KnowledgeGraph {

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @NonNull
    private KnowledgeGraphType type;

    @Getter
    @Setter
    private List<KnowledgeGraph> nestedGraphs;

    @Getter
    @Setter
    private List<Relation> relations;
}
