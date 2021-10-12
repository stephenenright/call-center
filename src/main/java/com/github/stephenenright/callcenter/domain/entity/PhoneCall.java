package com.github.stephenenright.callcenter.domain.entity;

import com.github.stephenenright.callcenter.domain.commands.CreatePhoneCallRequest;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PhoneCall {

    private final String firstName;
    private final String lastName;
    // TODO we could use a ZonedDateTime for this
    private final String timestamp;
    private final String sip;
    private final String city;
    private final String state;
    private final String phoneNumber;
    private final Integer priority;

    public static PhoneCall create(CreatePhoneCallRequest request) {
        return PhoneCall.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .timestamp(request.getTimestamp())
                .sip(request.getSip())
                .city(request.getCity())
                .state(request.getState())
                .phoneNumber(request.getPhoneNumber())
                .priority(request.getPriority())
                .build();
    }

}
