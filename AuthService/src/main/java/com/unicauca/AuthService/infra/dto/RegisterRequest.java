package com.unicauca.AuthService.infra.dto;

import com.unicauca.AuthService.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
}
