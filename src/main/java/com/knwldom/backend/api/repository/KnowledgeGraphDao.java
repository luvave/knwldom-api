package com.knwldom.backend.api.repository;

import com.knwldom.backend.api.components.StardogConnection;
import com.knwldom.backend.api.model.KnowledgeGraph;
import com.knwldom.backend.api.model.KnowledgeGraphType;
import com.knwldom.backend.api.model.UserGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.knwldom.backend.api.repository.Constants.PREFIXES;
import static com.knwldom.backend.api.repository.Constants.URI_PREFIX;
import static com.knwldom.backend.api.utils.StardogHelpers.getLabelFromBindingSet;
import static com.knwldom.backend.api.utils.StardogHelpers.stripURIPrefix;

@Repository
public class KnowledgeGraphDao {

    @Autowired
    private StardogConnection stardogConnection;

    public void createKnowledgeGraph(String userId, String graphUri, String graphName, boolean isDefault, String graphTypeUri) {
        String SPARQL_QUERY_CREATE_KNOWLEDGEGRAPH =
                PREFIXES +
                        "INSERT {" +
                        "  ?graphUri rdf:type knwldom:knowledgeGraph ;" +
                        "    knwldom:graphName ?graphName ;" +
                        "    knwldom:graphType ?graphTypeUri ." +
                        "  ?userUriGraphUri rdf:type knwldom:userGraph ;" +
                        "    knwldom:isDefault ?isDefault ;" +
                        "    knwldom:graph ?graphUri ." +
                        "  ?userUri knwldom:hasKnowledgeGraph ?userUriGraphUri ." +
                        "} WHERE {" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", \"user\", ?userId)) AS ?userUri)" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", \"user\", ?userId, ?graphUriStr)) AS ?userUriGraphUri)" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", ?graphUriStr)) AS ?graphUri)" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", ?graphTypeUriStr)) AS ?graphTypeUri)" +
                        "  BIND(?graphNameStr AS ?graphName)" +
                        "  BIND(?isDefaultStr AS ?isDefault)" +
                        "}";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("graphUriStr", graphUri);
        parameters.put("graphNameStr", graphName);
        parameters.put("isDefaultStr", String.valueOf(isDefault));
        parameters.put("graphTypeUriStr", graphTypeUri);

        stardogConnection.getSnarlTemplate().update(SPARQL_QUERY_CREATE_KNOWLEDGEGRAPH, parameters);
    }

    public List<UserGraph> getUserGraphs(String userId) {
        String SPARQL_QUERY_GET_USER_GRAPHS =
                PREFIXES +
                        "SELECT ?knowledgeGraphUri ?graphName ?isDefault ?graphTypeUri ?graphLabel " +
                        "WHERE {" +
                        "  BIND(IRI(CONCAT(\" " + URI_PREFIX + "\", \"user\", ?userId)) AS ?userUri)" +
                        "  ?userUri knwldom:hasKnowledgeGraph ?userGraph ." +
                        "  ?userGraph knwldom:graph ?knowledgeGraphUri ." +
                        "  ?knowledgeGraphUri knwldom:graphName ?graphName ." +
                        "  ?knowledgeGraphUri knwldom:graphType ?graphTypeUri ." +
                        "  ?graphTypeUri rdfs:label ?graphLabel ." +
                        "  ?userGraph knwldom:isDefault ?isDefault ." +
                        "}";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);

        return stardogConnection.getSnarlTemplate().query(
                SPARQL_QUERY_GET_USER_GRAPHS,
                parameters,
                (bindingSet) -> {
                    String knowledgeGraphUri = getLabelFromBindingSet(bindingSet, "knowledgeGraphUri");
                    String graphName = getLabelFromBindingSet(bindingSet, "graphName");
                    String graphTypeLabel = getLabelFromBindingSet(bindingSet, "graphLabel");
                    String graphTypeUri = getLabelFromBindingSet(bindingSet, "graphTypeUri");
                    boolean isDefault = Boolean.parseBoolean(getLabelFromBindingSet(bindingSet, "isDefault"));

                    KnowledgeGraph knowledgeGraph = new KnowledgeGraph();
                    KnowledgeGraphType type = new KnowledgeGraphType();
                    type.setGraphUri(stripURIPrefix(graphTypeUri));
                    type.setGraphType(graphTypeLabel);
                    knowledgeGraph.setKnowledgeGraphType(type);
                    knowledgeGraph.setGraphUri(stripURIPrefix(knowledgeGraphUri));
                    knowledgeGraph.setGraphName(graphName);

                    UserGraph userGraph = new UserGraph();
                    userGraph.setGraph(knowledgeGraph);
                    userGraph.setIsDefault(isDefault);

                    return userGraph;
                }
        );
    }

    public List<KnowledgeGraphType> getKnowledgeGraphTypes() {
        String SPARQL_QUERY_GET_GRAPH_TYPES = PREFIXES +
                "SELECT ?graphType ?graphTypeName " +
                "WHERE {" +
                "  ?graphType rdf:type knwldom:knowledgeGraphType ;" +
                "             rdfs:label ?graphTypeName ." +
                "}";

        return stardogConnection.getSnarlTemplate().query(
                SPARQL_QUERY_GET_GRAPH_TYPES,
                (bindingSet) -> {
                    String graphUri = getLabelFromBindingSet(bindingSet, "graphType");
                    String graphType = getLabelFromBindingSet(bindingSet, "graphTypeName");

                    KnowledgeGraphType knowledgeGraphType = new KnowledgeGraphType();
                    knowledgeGraphType.setGraphType(graphType);
                    knowledgeGraphType.setGraphUri(stripURIPrefix(graphUri));

                    return knowledgeGraphType;
                }
        );
    }
}
