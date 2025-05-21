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
        
        // Usuarios estudiantes (agregar si no existen)
        crearUsuarioSiNoExiste("jhonatan@email.com", "1234", Role.STUDENT);
        crearUsuarioSiNoExiste("natalia@email.com", "1234", Role.STUDENT);
        crearUsuarioSiNoExiste("juan@email.com", "1234", Role.STUDENT);
    }
    
    //Esto es para probar, en la tercera iteracion de ser necesario se elimina
    private void crearUsuarioSiNoExiste(String email, String password, Role role) {
        if (!userRepository.existsByEmail(email)) {
            String encodedPassword = passwordEncoder.encode(password);
            User user = User.builder()
                    .email(email)
                    .password(encodedPassword)
                    .role(role)
                    .build();
            userRepository.save(user);
            System.out.println("Usuario estudiante " + email + " creado");
        } else {
            System.out.println("El usuario estudiante " + email + " ya existe");
        }
    }
}
