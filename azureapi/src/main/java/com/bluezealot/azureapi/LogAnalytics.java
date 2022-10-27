package com.bluezealot.azureapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String authenticate() throws Exception{
        FormBodyPublisher bdBuilder = FormBodyPublisher.newBuilder()
        .query("grant_type", config.getGrantType())
        .query("client_id", config.getClientId())
        .query("client_secret", config.getSecretKey())
        .query("resource", config.getLogAnalyticsApi())
        .build();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(config.getAuthUri() + config.getTenantId() + "/oauth2/token"))
        .headers("Content-Type", "application/x-www-form-urlencoded")
        .POST(bdBuilder)
        .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if(response.statusCode() != 200){
            throw new AuthenticationException("Http error code: " + response.statusCode() + ". Detail message: " + response.body());
        }
        Map<String, String> map = convertBodyToMap(response.body());
        return response.body();
    }

    private Map<String, String> convertBodyToMap(String bodyString) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(bodyString, Map.class);
        return map;
    }
    
}