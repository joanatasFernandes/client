package com.clientapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "client-api", ignoreUnknownFields = false)
public class ApplicationProperties {
    private String jwtSecret;
    private int encryptStrength;
}
