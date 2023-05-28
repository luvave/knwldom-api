package com.knwldom.backend.api.controller;

import com.knwldom.backend.api.controller.dto.KnowledgeGraphTypeDto;
import com.knwldom.backend.api.controller.dto.NewKnowledgeGraphDto;
import com.knwldom.backend.api.controller.dto.UserGraphDto;
import com.knwldom.backend.api.services.KnowledgeGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/graph", produces = MediaType.APPLICATION_JSON_VALUE)
public class KnowledgeGraphController {

    @Autowired
    KnowledgeGraphService knowledgeGraphService;

    @PostMapping("/add")
    public ResponseEntity<?> createKnowledgeGraph(@RequestBody NewKnowledgeGraphDto newGraphDto) {
        knowledgeGraphService.createKnowledgeGraphForUser(newGraphDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}/all")
    @ResponseBody
    public ResponseEntity<List<UserGraphDto>> getGraphsForTheUser(@PathVariable String userId) {
        List<UserGraphDto> graphList = knowledgeGraphService.getAllGraphForUser(userId);
        return new ResponseEntity<List<UserGraphDto>>(graphList, HttpStatus.OK);
    }

    @GetMapping(value = "/types")
    @ResponseBody
    public ResponseEntity<List<KnowledgeGraphTypeDto>> getGraphTypes() {
        List<KnowledgeGraphTypeDto> typesList = knowledgeGraphService.getAllKnowledgeGraphTypes();
        return new ResponseEntity<List<KnowledgeGraphTypeDto>>(typesList, HttpStatus.OK);
    }
}
