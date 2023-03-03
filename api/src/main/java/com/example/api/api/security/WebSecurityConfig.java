package com.example.api.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author junyeong.jo .
 * @since 2023-02-28
 */

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
     private final JwtAuthFilter jwtAuthFilter;

    WebSecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }


    /*
     * Spring Security Configuration
     * 1. url starts with "open-api" no need auth
     * 2. url starts with "api" need auth (jwt token at header)
     * 3. localhost is open
     * 4. CSRF disabled
     * 5. Cors policy
     *
     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable(); // Cross-Site Request Forgery Disable

        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/open-api/**").permitAll()
                .requestMatchers("/api").authenticated();
        return httpSecurity.build();
    }
}
