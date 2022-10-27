package com.bluezealot.azureapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mizosoft.methanol.FormBodyPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogAnalytics {

    @Autowired
    private AppConfig config;
    /**
     * Get the authorization.
     * @return token
     * @throws Throwable URI Syntax error etc.
     */
    public String authorize() throws Exception{
        FormBodyPublisher bdBuilder = FormBodyPublisher.newBuilder()
        .query("grant_type", config.getGrantType())
        .query("client_id", config.getClientId())
        .query("client_secret", config.getSecretKey())
        .query("resource", config.getLogAnalyticsApi())
        .build();
        // BodyPublisher bodyContent = new BodyPublisher();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(config.getAuthUri() + config.getTenantId() + "/oauth2/token"))
        .headers("Content-Type", "application/x-www-form-urlencoded")
        .POST(bdBuilder)
        .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        log.info(response.body());
        return response.body();
    }
    
}