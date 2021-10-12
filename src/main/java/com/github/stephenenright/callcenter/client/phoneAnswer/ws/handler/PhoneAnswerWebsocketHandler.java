package com.github.stephenenright.callcenter.client.phoneAnswer.ws.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stephenenright.callcenter.client.phoneAnswer.ws.model.PhoneCallMessage;
import com.github.stephenenright.callcenter.domain.commands.CreatePhoneCallRequest;
import com.github.stephenenright.callcenter.domain.service.PhoneCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class PhoneAnswerWebsocketHandler extends TextWebSocketHandler {

    private static final String INVALID_MESSAGE = "\"\"this is intentionally bad data and should be discarded\"";

    private ObjectMapper objectMapper;
    private PhoneCallService phoneCallService;

    public PhoneAnswerWebsocketHandler(ObjectMapper objectMapper, PhoneCallService phoneCallService) {
        this.objectMapper = objectMapper;
        this.phoneCallService = phoneCallService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage txtMessage) throws Exception {
        if (txtMessage == null) { //TODO MAYBE LOG WARNING
            return;
        }

        final String messageStr = txtMessage.getPayload();

        if (!StringUtils.hasText(messageStr)) {
            log.debug("Ignoring Empty Message");
            return;
        }

        try {
            final PhoneCallMessage message = objectMapper.readValue(messageStr, PhoneCallMessage.class);
            log.info("Received incoming phone call message {}", message);
            CreatePhoneCallRequest request = CreatePhoneCallRequest.builder()
                    .firstName(message.getFirstName())
                    .lastName(message.getLastName())
                    .timestamp(message.getTimestamp())
                    .sip(message.getSip())
                    .city(message.getCity())
                    .state(message.getState())
                    .phoneNumber(message.getPhoneNumber())
                    .priority(message.getPriority())
                    .build();

            phoneCallService.push(request);

        } catch (Exception e) {
            log.warn("Ignoring invalid message {}", messageStr);
        }
    }
}
