package com.example.api.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author junyeong.jo .
 * @since 2023-03-06
 */
@Component
public class JwtManager {
    private final JwtProperty jwtProperty;
    private final ObjectMapper objectMapper;

    public JwtManager(JwtProperty jwtProperty, ObjectMapper objectMapper) {
        this.jwtProperty = jwtProperty;
        this.objectMapper = objectMapper;
    }
    public String generateAccessToken() {
        // test data setting //
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userKey", 1L);
        userInfo.put("companyKey", 1L);
        userInfo.put("departmentKey",1L);
        // end //
        Date now = Date.from(Instant.now());
        Date expireTime = Date.from(Instant.now().plusSeconds(jwtProperty.getAccessTokenValidTime()));
        return Jwts.builder()
                .setClaims(userInfo)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS512, jwtProperty.getAccessTokenSecret())
                .compact();
    }

    public String generateRefreshToken() {
        // test data setting //
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userKey", 1L);
        // end //
        Date now = Date.from(Instant.now());
        Date expireTime = Date.from(Instant.now().plusSeconds(jwtProperty.getRefreshTokenValidTime()));
        return Jwts.builder()
                .setClaims(userInfo)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS512, jwtProperty.getRefreshTokenSecret())
                .compact();
    }

    public Object parseAccessToken(String accToken) {
        /*
            Sample User Inform
         */

        Object userInfo = new Object();

        return userInfo;
    }

    public Authentication getAuthentication(String token) {
        User principal = new User("junyeongjo", "", null);
        return new UsernamePasswordAuthenticationToken(principal, token, null);
    }

}
