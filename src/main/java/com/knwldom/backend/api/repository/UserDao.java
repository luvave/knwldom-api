package com.knwldom.backend.api.repository;

import com.complexible.stardog.ext.spring.RowMapper;
import com.complexible.stardog.ext.spring.SnarlTemplate;
import com.knwldom.backend.api.components.StardogConnection;
import com.knwldom.backend.api.model.User;
import com.stardog.stark.query.BindingSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDao {

    private static final String PREFIX = "PREFIX knwldom: <http://knwldom.com/> ";
    private static final String SELECT_ALL_USERS = PREFIX +
            "SELECT ?user ?id ?name " +
            "WHERE { " +
            "  ?user a knwldom:User ; " +
            "        knwldom:id ?id ; " +
            "        knwldom:name ?name . " +
            "}";

    private static final String SELECT_USER_BY_ID(Integer id) {
        return PREFIX +
                "SELECT ?user ?name ?id" +
                "WHERE {" +
                "  ?user knwldom:id \"" + id +"\" ; " +
                "        knwldom:name ?name ; " +
                "        knwldom:id ?id . " +
                "}";
    }

    @Autowired
    private StardogConnection stardogConnection;

    public List<User> getAllUsers() {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        List<User> results = snarl.query(SELECT_ALL_USERS, new RowMapper<User>() {

            @Override
            public User mapRow(BindingSet bindingSet) {
                User u = new User();

                try {
                    String name = bindingSet.binding("name").get().literal().get().label();
                    String id = bindingSet.binding("id").get().literal().get().label();
                    u.setName(name);
                    u.setId(Integer.parseInt(id));

                    String connectionQuery =  PREFIX + "SELECT ?connection WHERE { ?user knwldom:id \"" + id + "\" ; knwldom:hasConnectionTo ?connection . }";
                    List<String> connections = snarl.query(connectionQuery, new RowMapper<String>() {
                        @Override
                        public String mapRow(BindingSet bindingSet) {
                            try {
                                return bindingSet.get("connection").toString();
                            } catch (Exception e) {
                                return "";
                            }

                        }
                    });
                    u.setHasConnectionTo(connections);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return u;
            }

            }
        );

        return results;
    }

    public List<User> getUserById(Integer id) {
        SnarlTemplate snarl = stardogConnection.getSnarlTemplate();
        List<User> results = snarl.query(SELECT_USER_BY_ID(id), new RowMapper<User>() {

                    @Override
                    public User mapRow(BindingSet bindingSet) {
                        User u = new User();

                        try {
                            String name = bindingSet.binding("name").get().literal().get().label();
                            u.setName(name);
                            u.setId(Integer.parseInt(id.toString()));
                            String connectionQuery =  PREFIX + "SELECT ?connection WHERE { ?user knwldom:id \"" + id + "\" ; knwldom:hasConnectionTo ?connection . }";
                            List<String> connections = snarl.query(connectionQuery, new RowMapper<String>() {
                                @Override
                                public String mapRow(BindingSet bindingSet) {
                                    try {
                                        return bindingSet.get("connection").toString();
                                    } catch (Exception e) {
                                        return "";
                                    }

                                }
                            });
                            u.setHasConnectionTo(connections);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return u;
                    }

                }
        );

        return results;
    }
}
