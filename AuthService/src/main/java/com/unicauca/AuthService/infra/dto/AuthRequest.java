package com.unicauca.AuthService.infra.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
