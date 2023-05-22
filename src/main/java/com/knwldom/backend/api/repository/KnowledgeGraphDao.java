package com.knwldom.backend.api.repository;

import com.complexible.stardog.ext.spring.SnarlTemplate;
import com.knwldom.backend.api.components.StardogConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KnowledgeGraphDao {

    @Autowired
    private StardogConnection stardogConnection;

    public void addUserKnowledgeGraph(String userUri, String graphName, boolean isDefault) {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        String sparqlQuery = String.format(
                "PREFIX knwldom: <http://knwldom.com/>\n" +
                        "INSERT DATA {\n" +
                        "  knwldom:%s knwldom:hasKnowledgeGraph knwldom:%s .\n" +
                        "  knwldom:%s a knwldom:knowledgeGraph ;\n" +
                        "    knwldom:graphName \"%s\" ;\n" +
                        "    knwldom:isDefault %s .\n" +
                        "}",
                userUri, graphName, graphName, graphName, isDefault);
        snarl.update(sparqlQuery);
    }
}
