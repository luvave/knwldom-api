package com.knwldom.backend.api.services;

import com.knwldom.backend.api.controller.dto.NewRelationDto;
import com.knwldom.backend.api.controller.exceptions.Api500Exception;
import com.knwldom.backend.api.repository.KnowledgeGraphDao;
import com.knwldom.backend.api.repository.RelationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationService {

    @Autowired
    RelationDao relationDao;

    public void addRelationToGraph(NewRelationDto newRelationDto) {
        try {
            relationDao.addRelationToGraph(newRelationDto.getGraphUri(), newRelationDto.getRelationName(), newRelationDto.getTo());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while adding the relation");
        }
    }
}
