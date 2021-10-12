package com.github.stephenenright.callcenter.domain.service;

import com.github.stephenenright.callcenter.domain.commands.CreatePhoneCallRequest;
import com.github.stephenenright.callcenter.domain.entity.PhoneCall;

import java.util.Optional;

public interface PhoneCallService {

    Optional<PhoneCall> peek();

    //TODO change return type to return response object with validation errors etc
    Optional<PhoneCall> push(CreatePhoneCallRequest request);

    Optional<PhoneCall> pop();

}
