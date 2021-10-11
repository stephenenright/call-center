package com.github.stephenenright.callcenter.client.phoneAnswer.ws.configuration.settings;

import com.github.stephenenright.callcenter.infrastructure.configuration.settings.ServiceConfigurationSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("call-center.service.phone-answer")
public class PhoneAnswerServiceSettings extends ServiceConfigurationSettings {


}
