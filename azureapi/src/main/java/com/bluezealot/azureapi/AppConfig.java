package com.bluezealot.azureapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("custom")
@Data
public class AppConfig {
    private String authUri;
    private String logAnalyticsApi;
    private String grantType;
    private String logAnalyticsWorkspaceId;  
    private String tenantId;
    private String clientId;
    private String secretKey;
}
