package com.github.stephenenright.callcenter.domain.service.impl;

import com.github.stephenenright.callcenter.common.validation.ValidationErrorBuilder;
import com.github.stephenenright.callcenter.domain.commands.CreatePhoneCallRequest;
import com.github.stephenenright.callcenter.domain.entity.PhoneCall;
import com.github.stephenenright.callcenter.domain.repository.PhoneCallRepository;
import com.github.stephenenright.callcenter.domain.service.PhoneCallService;
import com.github.stephenenright.callcenter.domain.validator.PhoneCallValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PhoneCallServiceImpl implements PhoneCallService {

    private final PhoneCallRepository phoneCallRepository;
    private final PhoneCallValidator phoneCallValidator;


    public PhoneCallServiceImpl(PhoneCallRepository phoneCallRepository, PhoneCallValidator phoneCallValidator) {
        this.phoneCallRepository = phoneCallRepository;
        this.phoneCallValidator = phoneCallValidator;
    }


    @Override
    public Optional<PhoneCall> peek() {
        return phoneCallRepository.peek();
    }

    @Override
    public Optional<PhoneCall> pop() {
        return phoneCallRepository.pop();
    }


    //TODO Return a response model with validation errors, the created model etc
    @Override
    public Optional<PhoneCall> push(CreatePhoneCallRequest request) {
        if (request == null) {
            return Optional.empty();
        }

        final ValidationErrorBuilder errorBuilder = new ValidationErrorBuilder();
        final PhoneCall phoneCall = PhoneCall.create(request);

        if (!phoneCallValidator.isValid(phoneCall, errorBuilder)) {
            //TODO replace debug with response containing errors
            log.debug("Not adding Invalid Phone Call {}", errorBuilder.getErrors());
            return Optional.empty();
        }

        return Optional.of(phoneCallRepository.push(phoneCall));
    }
}
