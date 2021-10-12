package com.github.stephenenright.callcenter.domain.commands;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
@Data
public class CreatePhoneCallRequest {

    private final String firstName;
    private final String lastName;
    private final String timestamp;
    private final String sip;
    private final String city;
    private final String state;
    private final String phoneNumber;
    private final Integer priority;
}
