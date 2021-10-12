package com.github.stephenenright.callcenter.common.validation;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple class to track validation errors for a unit of work
 */
public class ValidationErrorBuilder {

    //TODO a list may be better to track multiple errors for a key
    private Map<String, ValidationError> validationErrors = new HashMap<>();

    public ValidationErrorBuilder addStringRequiredIfNecessary(String key, String value) {
        if (!ValidationUtils.required(value)) {
            addRequired(key);
        }

        return this;
    }

    public ValidationErrorBuilder addRequiredIfNecessary(String key, Object value) {
        if (!ValidationUtils.required(value)) {
            addRequired(key);
        }

        return this;
    }

    public ValidationErrorBuilder addRequired(String key) {
        //todo improve this
        validationErrors.put(key, ValidationError.builder().key(key).message("required").build());
        return this;
    }

    public Map<String, ValidationError> getErrors() {
        return validationErrors;
    }

    public boolean hasErrors() {
        return validationErrors != null && validationErrors.size() > 0;
    }
}
