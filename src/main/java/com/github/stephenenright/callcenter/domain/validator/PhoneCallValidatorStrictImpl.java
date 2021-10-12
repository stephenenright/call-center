package com.github.stephenenright.callcenter.domain.validator;

import com.github.stephenenright.callcenter.common.validation.ValidationErrorBuilder;
import com.github.stephenenright.callcenter.common.validation.ValidationUtils;
import com.github.stephenenright.callcenter.domain.entity.PhoneCall;
import org.springframework.stereotype.Component;

/**
 * A Strict Phone validator that validates that all fields have being set
 */
@Component
public class PhoneCallValidatorStrictImpl implements PhoneCallValidator {

    @Override
    public boolean isValid(PhoneCall phoneCall, ValidationErrorBuilder builder) {
        if (phoneCall == null) {
            return false;
        }

        builder.addRequiredIfNecessary("priority", phoneCall.getPriority());
        builder.addRequiredIfNecessary("firstName", phoneCall.getFirstName());
        builder.addRequiredIfNecessary("lastName", phoneCall.getLastName());
        builder.addRequiredIfNecessary("phoneNumber", phoneCall.getPhoneNumber());
        builder.addRequiredIfNecessary("city", phoneCall.getCity());
        builder.addRequiredIfNecessary("state", phoneCall.getState());

        if (!ValidationUtils.isIsoDate(phoneCall.getTimestamp())) {
            builder.addRequired("timestamp"); //TODO Improve this
        }

        //TODO MAYBE THIS IS NOT NEEDED
        builder.addRequiredIfNecessary("sip", phoneCall.getSip());

        return !builder.hasErrors();
    }
}
