package com.example.api.api.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author junyeong.jo .
 * @since 2023-03-06
 */

@ConfigurationProperties("jwt")
@Data
public class JwtProperty {
    private final String accessTokenSecret;
    private final String refreshTokenSecret;
    private final Long accessTokenValidTime;
    private final Long refreshTokenValidTime;
}
