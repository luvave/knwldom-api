package com.knwldom.backend.api.utils;

import com.stardog.stark.Literal;
import com.stardog.stark.Value;
import com.stardog.stark.query.BindingSet;
import lombok.SneakyThrows;

public class StardogHelpers {

    @SneakyThrows
    public static String getLabelFromBindingSet(BindingSet bindingSet, String bindingName) {
        if (bindingSet.value(bindingName).isPresent()) {
            Value value = bindingSet.value(bindingName).get();
            if (value instanceof Literal) {
                return ((Literal) value).label();
            }
        }
        throw new Exception("Failed getting " + bindingName);
    }
}
