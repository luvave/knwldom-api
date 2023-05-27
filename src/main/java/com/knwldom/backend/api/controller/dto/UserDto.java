package com.knwldom.backend.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String userId;
    private String displayName;
    private List<UserDto> friends;
    private UserGraphDto userGraph;
}
