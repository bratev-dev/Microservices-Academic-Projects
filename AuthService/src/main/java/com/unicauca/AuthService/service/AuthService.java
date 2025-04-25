package com.unicauca.AuthService.service;

import com.unicauca.AuthService.infra.dto.AuthRequest;
import com.unicauca.AuthService.infra.dto.RegisterRequest;
import com.unicauca.AuthService.entity.User;
import com.unicauca.AuthService.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya esta registrado");
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        userRepository.save(user);

        return "Usuario registrado exitosamente";
    }

    public String authenticate(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return "Autenticación exitosa para el usuario: " + user.getEmail();
    }

}
