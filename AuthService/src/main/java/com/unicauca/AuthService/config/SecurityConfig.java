package com.unicauca.AuthService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desactivar CSRF para Postman
                .authorizeHttpRequests(auth -> auth
                       // .requestMatchers("/api/auth/register", "/api/auth/login", "api/auth/user/{email}").permitAll() // permitir estos endpoints
                        .anyRequest().permitAll() // Permite a todos sin login
                );

        return http.build();
    }
}
