package com.knwldom.backend.api.repository;

import com.knwldom.backend.api.components.StardogConnection;
import com.knwldom.backend.api.model.KnowledgeGraph;
import com.knwldom.backend.api.model.Relation;
import com.knwldom.backend.api.model.RelationType;
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
public class RelationDao {

    @Autowired
    private StardogConnection stardogConnection;

    public void createRelationType(String relationTypeUri, String relationTypeName) {
        String SPARQL_QUERY_CREATE_RELATION_TYPE =
                PREFIXES +
                        "INSERT {" +
                        "  ?relationTypeUri rdf:type knwldom:relationType ;" +
                        "    knwldom:relationName ?relationTypeName ." +
                        "}" +
                        "WHERE {" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", ?relationTypeUriStr)) AS ?relationTypeUri)" +
                        "  BIND(?relationTypeNameStr AS ?relationTypeName)" +
                        "  FILTER NOT EXISTS {" +
                        "    ?relationTypeUri rdf:type knwldom:relationType ." +
                        "  }" +
                        "}";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("relationTypeUriStr", relationTypeUri);
        parameters.put("relationTypeNameStr", relationTypeName);

        stardogConnection.getSnarlTemplate().update(SPARQL_QUERY_CREATE_RELATION_TYPE, parameters);
    }

    public List<RelationType> getAllRelationTypes() {
        String SPARQL_QUERY_GET_ALL_RELATION_TYPES =
                PREFIXES +
                        "SELECT ?relationTypeUri ?relationTypeName " +
                        "WHERE {" +
                        "  ?relationTypeUri rdf:type knwldom:relationType ;" +
                        "               knwldom:relationName ?relationTypeName ." +
                        "}";

        return stardogConnection.getSnarlTemplate().query(
                SPARQL_QUERY_GET_ALL_RELATION_TYPES,
                (bindingSet) -> {
                    String relationTypeUri = getLabelFromBindingSet(bindingSet, "relationTypeUri");
                    String relationTypeName = getLabelFromBindingSet(bindingSet, "relationTypeName");

                    RelationType relationType = new RelationType();
                    relationType.setRelationUri(stripURIPrefix(relationTypeUri));
                    relationType.setRelationName(relationTypeName);

                    return relationType;
                }
        );
    }

    public List<Relation> getRelationsForGraph(String graphUri) {
        String SPARQL_QUERY_GET_RELATIONS_FOR_GRAPH =
                PREFIXES +
                        "SELECT ?relationUri ?from ?typeUri ?typeName ?to " +
                        "WHERE {" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", ?graphUriStr)) AS ?graphUri)" +
                        "  ?relationUri rdf:type knwldom:relation ;" +
                        "               knwldom:from ?graphUri ;" +
                        "               knwldom:to ?to ." +
                        "  BIND(?graphUri AS ?from)" +
                        "  OPTIONAL {" +
                        "    ?relationUri rdfs:subClassOf ?typeUri ." +
                        "    ?typeUri knwldom:relationName ?typeName ." +
                        "  }" +
                        "}";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("graphUriStr", graphUri);

        return stardogConnection.getSnarlTemplate().query(
                SPARQL_QUERY_GET_RELATIONS_FOR_GRAPH,
                parameters,
                (bindingSet) -> {
                    String relationUri = getLabelFromBindingSet(bindingSet, "relationUri");
                    String from = getLabelFromBindingSet(bindingSet, "from");
                    String typeUri = getLabelFromBindingSet(bindingSet, "typeUri");
                    String typeName = getLabelFromBindingSet(bindingSet, "typeName");
                    String to = getLabelFromBindingSet(bindingSet, "to");

                    RelationType type = new RelationType();
                    type.setRelationUri(stripURIPrefix(typeUri));
                    type.setRelationName(typeName);
                    Relation relation = new Relation();
                    relation.setRelationType(type);
                    relation.setTo(to);
                    relation.setRelationUri(stripURIPrefix(relationUri));
                    relation.setFrom(stripURIPrefix(from));
                    return relation;
                }
        );
    }

    public void createRelation(String relationUri, String from, String to, String typeUri) {
        String SPARQL_QUERY_CREATE_RELATION =
                PREFIXES +
                        "INSERT {" +
                        "  ?relationUri rdf:type knwldom:relation ;" +
                        "    knwldom:from ?fromUri ;" +
                        "    knwldom:to ?toUri ;" +
                        "    rdfs:subClassOf ?typeUri ." +
                        "}" +
                        "WHERE {" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", ?relationUriStr)) AS ?relationUri)" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", ?from)) AS ?fromUri)" +
                        "  BIND(?to AS ?toUri)" +
                        "  BIND(IRI(CONCAT(\"" + URI_PREFIX + "\", ?typeUriStr)) AS ?typeUri)" +
                        "}";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("relationUriStr", relationUri);
        parameters.put("from", from);
        parameters.put("to", to);
        parameters.put("typeUriStr", typeUri);

        stardogConnection.getSnarlTemplate().update(SPARQL_QUERY_CREATE_RELATION, parameters);
    }
}
