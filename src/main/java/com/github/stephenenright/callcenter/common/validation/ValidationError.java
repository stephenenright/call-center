package com.github.stephenenright.callcenter.common.validation;

import lombok.Builder;
import lombok.Value;

/**
 * A simple validation error;
 */
@Builder
@Value
public class ValidationError {

    private final String key;
    private final String message;

}
