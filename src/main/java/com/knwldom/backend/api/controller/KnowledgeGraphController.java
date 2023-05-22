package com.knwldom.backend.api.controller;

import com.knwldom.backend.api.controller.dto.NewGraphDto;
import com.knwldom.backend.api.services.KnowledgeGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/graph", produces = MediaType.APPLICATION_JSON_VALUE)
public class KnowledgeGraphController {

    @Autowired
    KnowledgeGraphService knowledgeGraphService;

    @PostMapping("/add")
    public ResponseEntity<?> createKnowledgeGraph(@RequestBody NewGraphDto newGraphDto) {
        knowledgeGraphService.createKnowledgeGraph(newGraphDto);
        return ResponseEntity.ok().build();
    }
}
