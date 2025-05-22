package com.example.apigateway;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

// SecurityConfig.java en Gateway:
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/company/api/projects", "/company/api/projects/*").hasRole("coordinator")
                        .pathMatchers("/company/**").hasRole("company")
                        .pathMatchers("/coordinator/**").hasRole("coordinator")
                        .pathMatchers("/coordinator/api/projects").hasRole("student")
                        .pathMatchers("/student/**").hasRole("student")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );

        return http.build();
    }
}