package com.unicauca.AuthService.repository;

import com.unicauca.AuthService.entity.Role;
import com.unicauca.AuthService.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("coordinador@unicauca.com")) {
            String encodedPassword = passwordEncoder.encode("coord123"); // Contraseña encriptada
            User coordinador = User.builder()
                    .email("coordinador@unicauca.com")
                    .password(encodedPassword)
                    .role(Role.COORDINATOR)
                    .build();
            userRepository.save(coordinador);
            System.out.println("Usuario Coordinador predeterminado creado");
        } else {
            System.out.println("El usuario Coordinador ya existe");
        }
    }
}
