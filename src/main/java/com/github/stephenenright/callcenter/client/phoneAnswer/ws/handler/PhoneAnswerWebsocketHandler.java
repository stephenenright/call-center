package com.github.stephenenright.callcenter.client.phoneAnswer.ws.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stephenenright.callcenter.client.phoneAnswer.ws.model.PhoneCallMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class PhoneAnswerWebsocketHandler extends TextWebSocketHandler {

    private static final String INVALID_MESSAGE = "\"\"this is intentionally bad data and should be discarded\"";

    private ObjectMapper objectMapper;

    public PhoneAnswerWebsocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

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
            final PhoneCallMessage phoneCallMessage = objectMapper.readValue(messageStr, PhoneCallMessage.class);
            log.info("Received incoming phone call message {}", phoneCallMessage);
        } catch (Exception e) {
            log.warn("Ignoring invalid message {}", messageStr);
        }
    }
}
