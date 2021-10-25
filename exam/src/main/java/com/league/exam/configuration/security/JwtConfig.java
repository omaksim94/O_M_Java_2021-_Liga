package com.league.exam.configuration.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
@Getter
@Setter
public class JwtConfig {
    private String secretKey;

    public JwtConfig() {
    }
}
