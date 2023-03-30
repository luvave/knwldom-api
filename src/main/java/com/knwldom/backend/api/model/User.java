package com.knwldom.backend.api.model;

import lombok.*;

import java.util.List;

import java.io.Serializable;
@NoArgsConstructor
public class User implements Serializable {

    @Getter
    @Setter
    @NonNull
    private String name;
    @Getter
    @Setter
    @NonNull
    private int id;
    @Getter
    @Setter
    private List<String> hasConnectionTo;
}
