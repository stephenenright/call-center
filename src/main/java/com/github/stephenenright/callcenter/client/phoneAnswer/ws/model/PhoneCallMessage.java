package com.github.stephenenright.callcenter.client.phoneAnswer.ws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PhoneCallMessage {

    private final String firstName;
    private final String lastName;
    private final String timestamp;
    private final String sip;
    private final String city;
    private final String state;
    private final String phoneNumber;
    private final Integer priority;

}
