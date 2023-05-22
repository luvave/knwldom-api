package com.knwldom.backend.api.model;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
public class Relation {

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @NonNull
    private List<String> to;
}
