package com.socialnetwork.demo.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
@Data
public class JwtConfig {
    private String secretKey;

    public JwtConfig() {
    }
}
