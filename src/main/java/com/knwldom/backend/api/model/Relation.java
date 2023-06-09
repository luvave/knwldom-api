package com.knwldom.backend.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relation {
    private RelationType relationType;
    private String relationUri;
    private String from;
    private String to;
}
