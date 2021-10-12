package com.github.stephenenright.callcenter.domain.repository;

import com.github.stephenenright.callcenter.domain.entity.PhoneCall;

import java.util.Optional;

public interface PhoneCallRepository {

    PhoneCall push(PhoneCall phoneCall);

    Optional<PhoneCall> peek();

    Optional<PhoneCall> pop();

}
