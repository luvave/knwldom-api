package com.knwldom.backend.api.services;

import com.knwldom.backend.api.controller.dto.NewGraphDto;
import com.knwldom.backend.api.controller.exceptions.Api500Exception;
import com.knwldom.backend.api.repository.KnowledgeGraphDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeGraphService {

    @Autowired
    KnowledgeGraphDao knowledgeGraphDao;

    public void createKnowledgeGraph(NewGraphDto newGraphDto) {
        try {
            knowledgeGraphDao.addUserKnowledgeGraph(newGraphDto.getUserUri(), newGraphDto.getGraphName(), newGraphDto.isDefault());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while creating a graph");
        }
    }
}
