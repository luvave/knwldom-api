package com.knwldom.backend.api.services;

import com.knwldom.backend.api.controller.dto.RelationDto;
import com.knwldom.backend.api.repository.RelationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationService {

    @Autowired
    RelationDao connectionDao;

    public void addARelationToUser(RelationDto relationDto) {
        connectionDao.addARelation(relationDto.getUserId(), relationDto.getRelation());
    }

    public void removeARelationToUser(RelationDto relationDto) {
        connectionDao.deleteARelation(relationDto.getUserId(), relationDto.getRelation());
    }
}
