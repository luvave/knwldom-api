package com.knwldom.backend.api.repository;

public final class Constants {
    private Constants() {
        // private constructor to prevent instantiation
    }

    public static final String PREFIXES =
            "PREFIX knwldom: <http://knwldom.com/>" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>";

    public static final String URI_PREFIX = "http://knwldom.com/";

    public static final String USERID_PREFIX = "user";
}
