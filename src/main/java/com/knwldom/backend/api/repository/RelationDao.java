package com.knwldom.backend.api.repository;

import com.complexible.stardog.ext.spring.SnarlTemplate;
import com.knwldom.backend.api.components.StardogConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RelationDao {

    private static final String PREFIX = "PREFIX knwldom: <http://knwldom.com/> ";

    private static final String ADD_RELATION_BY_USER_ID(Integer id, String relation) {
        return PREFIX +
                "INSERT { " +
                "  ?user knwldom:hasConnectionTo " + relation + " . " +
                "} " +
                "WHERE { " +
                "  ?user knwldom:id \"" + id + "\" . " +
                "} ";
    }

    private static final String DELETE_RELATION_BY_USER_ID(Integer id, String relation) {
        return PREFIX +
                "DELETE {" +
                "  ?user knwldom:hasConnectionTo " + relation + " ." +
                "}" +
                "WHERE {" +
                "  ?user knwldom:id \"" + id + "\" ." +
                "}";
    }

    @Autowired
    private StardogConnection stardogConnection;

    public void addARelation(Integer id, String relation) {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        snarl.update(ADD_RELATION_BY_USER_ID(id, relation));
    }

    public void deleteARelation(Integer id, String relation) {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        snarl.update(DELETE_RELATION_BY_USER_ID(id, relation));
    }
}
