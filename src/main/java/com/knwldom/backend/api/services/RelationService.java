package com.knwldom.backend.api.services;

import com.knwldom.backend.api.controller.dto.NewKnowledgeGraphDto;
import com.knwldom.backend.api.controller.dto.RelationDto;
import com.knwldom.backend.api.controller.dto.RelationTypeDto;
import com.knwldom.backend.api.controller.dto.UserGraphDto;
import com.knwldom.backend.api.controller.exceptions.Api500Exception;
import com.knwldom.backend.api.model.Relation;
import com.knwldom.backend.api.model.RelationType;
import com.knwldom.backend.api.repository.RelationDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RelationDao relationDao;

    public void addRelationType(RelationTypeDto relationTypeDto) {
        try {
            relationDao.createRelationType(relationTypeDto.getRelationUri(), relationTypeDto.getRelationName());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while creating a relation type");
        }
    }

    public List<RelationTypeDto> findAllRelationTypes() {
        try {
            List<RelationType> relationTypes = relationDao.getAllRelationTypes();
            return relationTypes.stream().map(relType -> modelMapper.map(relType, RelationTypeDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while getting relation types");
        }
    }

    public List<RelationDto> findAllRelationsForGraph(String graphUri) {
        try {
            List<Relation> relations = relationDao.getRelationsForGraph(graphUri);
            return relations.stream().map(rel -> modelMapper.map(rel, RelationDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while getting relations");
        }
    }

    public void addRelationToGraph(RelationDto relationDto) {
        try {
            relationDao.createRelation(
                    relationDto.getRelationUri(),
                    relationDto.getFrom(),
                    relationDto.getTo(),
                    relationDto.getRelationType().getRelationUri()
            );
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while creating a relation type");
        }
    }
}
