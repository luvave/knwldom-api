package com.knwldom.backend.api.model;

import lombok.*;

import java.util.List;

import java.io.Serializable;
@RequiredArgsConstructor
public class User implements Serializable {

    @Getter
    @Setter
    @NonNull
    private String displayName;
    @Getter
    @Setter
    @NonNull
    private String userId;
    @Getter
    @Setter
    private List<User> hasFriend;

    @Getter
    @Setter
    private List<KnowledgeGraph> hasKnowledgeGraph;

    @Getter
    @Setter
    private KnowledgeGraph isDefault;
}
