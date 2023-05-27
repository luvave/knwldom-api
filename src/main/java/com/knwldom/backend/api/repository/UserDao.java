package com.knwldom.backend.api.repository;

import com.complexible.stardog.ext.spring.RowMapper;
import com.complexible.stardog.ext.spring.SnarlTemplate;
import com.knwldom.backend.api.components.StardogConnection;
import com.knwldom.backend.api.model.User;
import com.stardog.stark.query.BindingSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.knwldom.backend.api.repository.Constants.PREFIXES;
import static com.knwldom.backend.api.repository.Constants.URI_PREFIX;
import static com.knwldom.backend.api.utils.StardogHelpers.getLabelFromBindingSet;

@Repository
public class UserDao {
    @Autowired
    private StardogConnection stardogConnection;

    public List<User> getAllUsers() {
        String SPARQL_QUERY_GET_ALL_USERS =
                PREFIXES +
                        "SELECT ?user ?userId ?displayName " +
                        "WHERE {" +
                        "  ?user rdf:type knwldom:user ;" +
                        "        knwldom:userId ?userId ;" +
                        "        knwldom:displayName ?displayName ." +
                        "}";

        return stardogConnection.getSnarlTemplate().query(
                SPARQL_QUERY_GET_ALL_USERS,
                (bindingSet) -> {
                    String userId = getLabelFromBindingSet(bindingSet, "userId");
                    String displayName = getLabelFromBindingSet(bindingSet, "displayName");
                    User user = new User();
                    user.setUserId(userId);
                    user.setDisplayName(displayName);
                    return user;
                }
        );
    }

    public void createUser(User user) {
        String SPARQL_QUERY_CREATE_USER =
                PREFIXES +
                        "INSERT {" +
                        "  ?userUri rdf:type knwldom:user ;" +
                        "    knwldom:userId ?userId ;" +
                        "    knwldom:displayName ?displayName ." +
                        "} WHERE {" +
                        "  BIND(IRI(CONCAT(\" " + URI_PREFIX + "\", \"user\", ?userIdStr)) AS ?userUri)" +
                        "  BIND(?userIdStr AS ?userId)" +
                        "  BIND(?displayNameStr AS ?displayName)" +
                        "}";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userIdStr", user.getUserId());
        parameters.put("displayNameStr", user.getDisplayName());

        stardogConnection.getSnarlTemplate().update(SPARQL_QUERY_CREATE_USER, parameters);
    }
}
