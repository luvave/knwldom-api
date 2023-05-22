package com.knwldom.backend.api.repository;

import com.complexible.stardog.ext.spring.RowMapper;
import com.complexible.stardog.ext.spring.SnarlTemplate;
import com.knwldom.backend.api.components.StardogConnection;
import com.knwldom.backend.api.model.KnowledgeGraph;
import com.knwldom.backend.api.model.KnowledgeGraphType;
import com.knwldom.backend.api.model.User;
import com.stardog.stark.query.BindingSet;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.knwldom.backend.api.utils.StardogHelpers.getLabelFromBindingSet;

@Repository
public class UserDao {
    @Autowired
    private StardogConnection stardogConnection;

    public List<User> getAllUsers() {
        final String sparqlQuery =
                "PREFIX knwldom: <http://knwldom.com/>\n" +
                        "SELECT ?user ?userId ?displayName\n" +
                        "WHERE {\n" +
                        "  ?user a knwldom:user ;\n" +
                        "        knwldom:userId ?userId ;\n" +
                        "        knwldom:displayName ?displayName .\n" +
                        "}";

        return stardogConnection.getSnarlTemplate().query(sparqlQuery, new RowMapper<User>() {
            @Override
            public User mapRow(BindingSet bindingSet) {
                String userId = getLabelFromBindingSet(bindingSet, "userId");
                String displayName = getLabelFromBindingSet(bindingSet, "displayName");
                User user = new User(displayName, userId);
                user.setHasFriend(new ArrayList<>());
                user.setHasKnowledgeGraph(new ArrayList<>());
                return user;
            }
        });
    }

    public void createUser(User user) {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        String insertUserQuery = String.format(
                "PREFIX knwldom: <http://knwldom.com/>\n" +
                        "INSERT DATA {\n" +
                        "  knwldom:%s a knwldom:user ;\n" +
                        "    knwldom:userId \"%s\" ;\n" +
                        "    knwldom:displayName \"%s\" .\n" +
                        "}", user.getUserId(), user.getUserId(), user.getDisplayName());
        snarl.update(insertUserQuery);
    }

    public List<KnowledgeGraph> getKnowledgeGraphsForUser(String userId) {
        String sparqlQuery = "PREFIX knwldom: <http://knwldom.com/>\n" +
                "SELECT ?graph ?graphName ?isDefault ?type\n" +
                "WHERE {\n" +
                "  ?user a knwldom:user ;\n" +
                "        knwldom:userId \"" + userId + "\" .\n" +
                "  ?user knwldom:hasKnowledgeGraph ?graph .\n" +
                "  ?graph a knwldom:knowledgeGraph ;\n" +
                "         knwldom:graphName ?graphName ;\n" +
                "         knwldom:isDefault ?isDefault ;\n" +
                "         knwldom:type ?type .\n" +
                "}";

        return stardogConnection.getSnarlTemplate().query(sparqlQuery, new RowMapper<KnowledgeGraph>() {
            @Override
            public KnowledgeGraph mapRow(BindingSet bindingSet) {
                String graphUri = getLabelFromBindingSet(bindingSet, "graph");
                String graphName = getLabelFromBindingSet(bindingSet, "graphName");
                String isDefault = getLabelFromBindingSet(bindingSet, "isDefault");
                String type = getLabelFromBindingSet(bindingSet, "type");

                KnowledgeGraph knowledgeGraph = new KnowledgeGraph(graphName, new KnowledgeGraphType(type));
                return knowledgeGraph;
            }
        });
    }
}
