package com.unicauca.AuthService.service;

import com.unicauca.AuthService.infra.dto.AuthRequest;
import com.unicauca.AuthService.infra.dto.AuthResponse;
import com.unicauca.AuthService.infra.dto.RegisterRequest;
import com.unicauca.AuthService.entity.User;
import com.unicauca.AuthService.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("El email ya está registrado");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }


    public AuthResponse authenticate(AuthRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            return AuthResponse.builder()
                    .success(false)
                    .message("Usuario no encontrado")
                    .build();
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return AuthResponse.builder()
                    .success(false)
                    .message("Contraseña incorrecta")
                    .build();
        }

        return AuthResponse.builder()
                .success(true)
                .message("Autenticación exitosa")
                .email(user.getEmail())
                .role(user.getRole().name())
                .userId(user.getId())
                .build();
    }

}
