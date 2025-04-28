package com.unicauca.AuthService.controller;

import com.unicauca.AuthService.infra.dto.AuthRequest;
import com.unicauca.AuthService.infra.dto.AuthResponse;
import com.unicauca.AuthService.infra.dto.RegisterRequest;
import com.unicauca.AuthService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }

}
