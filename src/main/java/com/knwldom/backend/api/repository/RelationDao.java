package com.knwldom.backend.api.repository;

import com.complexible.stardog.ext.spring.SnarlTemplate;
import com.knwldom.backend.api.components.StardogConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RelationDao {

    @Autowired
    private StardogConnection stardogConnection;

    public void addRelationToGraph(String graphUri, String relationName, String to) {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        String sparqlQuery = String.format(
                "PREFIX knwldom: <http://knwldom.com/>\n" +
                        "INSERT DATA {\n" +
                        "  <%s> knwldom:contains [\n" +
                        "    a knwldom:Relation ;\n" +
                        "    knwldom:relationName \"%s\" ;\n" +
                        "    knwldom:to <%s> ;\n" +
                        "  ] .\n" +
                        "}",
                graphUri, relationName, to);
        snarl.update(sparqlQuery);
    }
}
