package com.knwldom.backend.api.repository;

import com.complexible.stardog.ext.spring.RowMapper;
import com.complexible.stardog.ext.spring.SnarlTemplate;
import com.knwldom.backend.api.components.StardogConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectionDao {

    private static final String PREFIX = "PREFIX knwldom: <http://knwldom.com/> ";

    private static final String ADD_CONNECTION_BY_USER_ID(Integer id, String connection) {
        return PREFIX +
                "INSERT { " +
                "  ?user knwldom:hasConnectionTo " + connection + " . " +
                "} " +
                "WHERE { " +
                "  ?user knwldom:id \"" + id + "\" . " +
                "} ";
    }

    private static final String DELETE_CONNECTION_BY_USER_ID(Integer id, String connection) {
        return PREFIX +
                "DELETE {" +
                "  ?user knwldom:hasConnectionTo " + connection + " ." +
                "}" +
                "WHERE {" +
                "  ?user knwldom:id \"" + id + "\" ." +
                "}";
    }

    @Autowired
    private StardogConnection stardogConnection;

    public void addAConnection(Integer id, String connection) {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        snarl.update(ADD_CONNECTION_BY_USER_ID(id, connection));
    }

    public void deleteAConnection(Integer id, String connection) {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        snarl.update(DELETE_CONNECTION_BY_USER_ID(id, connection));
    }
}
