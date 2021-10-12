package com.github.stephenenright.callcenter.application.controller;

import com.github.stephenenright.callcenter.domain.entity.PhoneCall;
import com.github.stephenenright.callcenter.domain.service.PhoneCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phone-calls")
public class PhoneCallController {

    private final PhoneCallService phoneCallService;

    public PhoneCallController(PhoneCallService phoneCallService) {
        this.phoneCallService = phoneCallService;
    }

    //TODO generally we should return a model (view of the domain model) for the client rather than the domain model
    @PutMapping("/pop")
    public ResponseEntity<PhoneCall> pop() {
        return ResponseEntity.of(phoneCallService.pop()); //simple return
    }
}
