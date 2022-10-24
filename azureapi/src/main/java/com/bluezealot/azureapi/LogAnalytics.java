package com.bluezealot.azureapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.mizosoft.methanol.FormBodyPublisher;

public class LogAnalytics {

    @Autowired
    private AppConfig config;
    /**
     * Get the authorization.
     * @return token
     * @throws Throwable URI Syntax error etc.
     */
    public String authorize() throws Throwable{
        FormBodyPublisher bdBuilder = FormBodyPublisher.newBuilder()
        .query("grant_type", config.getGrantType())
        .query("client_id", config.getClientId())
        .query("client_secret", config.getSecretKey())
        .build();
        // BodyPublisher bodyContent = new BodyPublisher();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(config.getAuthUri()))
        .headers("Content-Type", "x-www-form-urlencoded")
        .POST(bdBuilder)
        .build();
        return null;
    }
    
}