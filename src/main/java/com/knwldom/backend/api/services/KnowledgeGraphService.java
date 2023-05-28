package com.knwldom.backend.api.services;

import com.knwldom.backend.api.controller.dto.KnowledgeGraphTypeDto;
import com.knwldom.backend.api.controller.dto.NewKnowledgeGraphDto;
import com.knwldom.backend.api.controller.dto.UserGraphDto;
import com.knwldom.backend.api.controller.exceptions.Api500Exception;
import com.knwldom.backend.api.model.KnowledgeGraphType;
import com.knwldom.backend.api.model.UserGraph;
import com.knwldom.backend.api.repository.KnowledgeGraphDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeGraphService {

    @Autowired
    KnowledgeGraphDao knowledgeGraphDao;

    @Autowired
    private ModelMapper modelMapper;

    public void createKnowledgeGraphForUser(NewKnowledgeGraphDto newKnowledgeGraphDto) {
        try {
            knowledgeGraphDao.createKnowledgeGraph(newKnowledgeGraphDto.getUserId(),
                    newKnowledgeGraphDto.getGraphUri(),
                    newKnowledgeGraphDto.getGraphName(),
                    newKnowledgeGraphDto.isDefault(),
                    newKnowledgeGraphDto.getGraphTypeUri());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while creating a graph");
        }
    }

    public List<UserGraphDto> getAllGraphForUser(String userId) {
        try {
            List<UserGraph> graphList = knowledgeGraphDao.getUserGraphs(userId);
            return graphList.stream().map(graph -> modelMapper.map(graph, UserGraphDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while fetching graphs for the user");
        }
    }

    public List<KnowledgeGraphTypeDto> getAllKnowledgeGraphTypes() {
        try {
            List<KnowledgeGraphType> typeList = knowledgeGraphDao.getKnowledgeGraphTypes();
            return typeList.stream().map(type -> modelMapper.map(type, KnowledgeGraphTypeDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Api500Exception("Error occurred while fetching graph types");
        }
    }
}
