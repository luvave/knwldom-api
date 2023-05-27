package com.knwldom.backend.api.controller;

import com.knwldom.backend.api.controller.dto.RelationDto;
import com.knwldom.backend.api.controller.dto.RelationTypeDto;
import com.knwldom.backend.api.services.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/relation", produces = MediaType.APPLICATION_JSON_VALUE)
public class RelationController {

    @Autowired
    RelationService relationService;

    @GetMapping(value = "/types")
    @ResponseBody
    public ResponseEntity<List<RelationTypeDto>> getAllRelationTypes() {
        List<RelationTypeDto> typesList = relationService.findAllRelationTypes();
        return new ResponseEntity<List<RelationTypeDto>>(typesList, HttpStatus.OK);
    }

    @PostMapping("/types/add")
    public ResponseEntity<?> addRelationType(@RequestBody RelationTypeDto relationTypeDto) {
        relationService.addRelationType(relationTypeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{graphUri}")
    @ResponseBody
    public ResponseEntity<List<RelationDto>> getRelationForGraph(@PathVariable String graphUri) {
        List<RelationDto> typesList = relationService.findAllRelationsForGraph(graphUri);
        return new ResponseEntity<List<RelationDto>>(typesList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRelation(@RequestBody RelationDto relationDto) {
        relationService.addRelationToGraph(relationDto);
        return ResponseEntity.ok().build();
    }
}
