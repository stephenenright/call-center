package com.github.stephenenright.callcenter.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stephenenright.callcenter.client.phoneAnswer.ws.configuration.settings.PhoneAnswerServiceSettings;
import com.github.stephenenright.callcenter.client.phoneAnswer.ws.handler.PhoneAnswerWebsocketHandler;
import com.github.stephenenright.callcenter.common.utils.UriUtils;
import com.github.stephenenright.callcenter.domain.service.PhoneCallService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * Configures the phone answering service ws client
 */
@Configuration
public class PhoneAnswerClientConfiguration {

    //use what the framework offers -- could be better
    @Bean
    public WebSocketConnectionManager phoneAnswerWsClient(PhoneAnswerServiceSettings serviceSettings,
                                                          PhoneCallService phoneCallService, ObjectMapper mapper) {
        final WebSocketHandler wsHandler = new PhoneAnswerWebsocketHandler(mapper,phoneCallService);
        final WebSocketClient wsClient = new StandardWebSocketClient();
        WebSocketConnectionManager manager = new WebSocketConnectionManager(wsClient, wsHandler, UriUtils.createUri(serviceSettings));
        manager.setAutoStartup(true);
        return manager;
    }
}
