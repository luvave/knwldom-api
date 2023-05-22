package com.knwldom.backend.api.controller.dto;

import com.knwldom.backend.api.model.KnowledgeGraph;
import com.knwldom.backend.api.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String displayName;
    private String userId;
    private List<UserDto> hasFriend;

    private List<GraphDto> hasKnowledgeGraph;
}
