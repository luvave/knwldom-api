package com.knwldom.backend.api.controller;

import com.knwldom.backend.api.controller.dto.RelationDto;
import com.knwldom.backend.api.services.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/relation", produces = MediaType.APPLICATION_JSON_VALUE)
public class RelationController {

    @Autowired
    RelationService relationService;

    @PostMapping(path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addARelationToUser(@RequestBody RelationDto relationDto) {

        relationService.addARelationToUser(relationDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/remove",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> removeARelationToUser(@RequestBody RelationDto relationDto) {

        relationService.removeARelationToUser(relationDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
