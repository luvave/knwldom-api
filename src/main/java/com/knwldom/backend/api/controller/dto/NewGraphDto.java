package com.knwldom.backend.api.controller.dto;

import lombok.Data;

@Data
public class NewGraphDto {

    String userUri;
    String graphName;

    boolean isDefault;
}
