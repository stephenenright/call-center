package com.github.stephenenright.callcenter.infrastructure.configuration.settings;

/**
 * Settings for connecting to a service
 */
public class ServiceConfigurationSettings {
    private String scheme;
    private String host;
    private String port;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
