package com.github.stephenenright.callcenter.client.phoneAnswer.ws.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PhoneCallMessage {

    @JsonAlias("first_name")
    private final String firstName;
    @JsonAlias("last_name")
    private final String lastName;
    private final String timestamp;
    private final String sip;
    private final String city;
    private final String state;
    @JsonAlias("phone_number")
    private final String phoneNumber;
    private final Integer priority;

}
