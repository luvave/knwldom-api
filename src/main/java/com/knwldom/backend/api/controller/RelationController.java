package com.knwldom.backend.api.controller;

import com.knwldom.backend.api.controller.dto.NewGraphDto;
import com.knwldom.backend.api.controller.dto.NewRelationDto;
import com.knwldom.backend.api.services.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/relation", produces = MediaType.APPLICATION_JSON_VALUE)
public class RelationController {

    @Autowired
    RelationService relationService;

    @PostMapping("/add")
    public ResponseEntity<?> createRelation(@RequestBody NewRelationDto newRelationDto) {
        relationService.addRelationToGraph(newRelationDto);
        return ResponseEntity.ok().build();
    }
}
