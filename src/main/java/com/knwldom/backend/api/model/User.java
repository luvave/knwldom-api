package com.knwldom.backend.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String displayName;
    private List<User> friends;
    private List<UserGraph> userGraphs; // updated this from UserGraph to List<UserGraph>
}