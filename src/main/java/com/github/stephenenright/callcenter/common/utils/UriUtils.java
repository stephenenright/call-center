package com.github.stephenenright.callcenter.common.utils;

import com.github.stephenenright.callcenter.infrastructure.configuration.settings.ServiceConfigurationSettings;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Helper class for working with uri's
 */
public class UriUtils {

    public static final String createUri(ServiceConfigurationSettings settings) {
        return UriComponentsBuilder.newInstance()
                .scheme(settings.getScheme())
                .host(settings.getHost())
                .port(settings.getPort()) //TODO Test if port is set
                .build().toUri().toString();
    }
}
