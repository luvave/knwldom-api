package com.knwldom.backend.api.utils;

import com.stardog.stark.Literal;
import com.stardog.stark.Value;
import com.stardog.stark.impl.IRIImpl;
import com.stardog.stark.query.BindingSet;
import lombok.SneakyThrows;

import static com.knwldom.backend.api.repository.Constants.URI_PREFIX;

public class StardogHelpers {

    @SneakyThrows
    public static String getLabelFromBindingSet(BindingSet bindingSet, String bindingName) {
        if (bindingSet.value(bindingName).isPresent()) {
            Value value = bindingSet.value(bindingName).get();
            if (value instanceof Literal) {
                return ((Literal) value).label();
            }
            if (value instanceof IRIImpl) {
                return ((IRIImpl) value).toString();
            }
        }
        throw new Exception("Failed getting " + bindingName);
    }

    public static String stripURIPrefix(String variable) {
        return variable.replaceFirst(URI_PREFIX, "");
    }

    public static String stripPrefix(String variable, String prefix) {
        return variable.replaceFirst(prefix, "");
    }
}
