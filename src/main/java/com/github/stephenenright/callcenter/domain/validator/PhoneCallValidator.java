package com.github.stephenenright.callcenter.domain.validator;

import com.github.stephenenright.callcenter.common.validation.ValidationErrorBuilder;
import com.github.stephenenright.callcenter.domain.entity.PhoneCall;

public interface PhoneCallValidator {

    boolean isValid(PhoneCall phoneCall, ValidationErrorBuilder builder);
}
