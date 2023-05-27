package com.knwldom.backend.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGraph {
    private KnowledgeGraph graph;
    private Boolean isDefault;
}
