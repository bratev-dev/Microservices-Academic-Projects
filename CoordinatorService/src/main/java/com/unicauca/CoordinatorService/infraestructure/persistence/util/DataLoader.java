package com.unicauca.CoordinatorService.infraestructure.persistence.util;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.JpaProjectSpringDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataLoader implements CommandLineRunner {

    private final JpaProjectSpringDataRepository projectRepository;

    public DataLoader(JpaProjectSpringDataRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Proyecto 1
        JpaProjectEntity jpaProjectEntity1 = new JpaProjectEntity();
        jpaProjectEntity1.setBudget(3500000);
        jpaProjectEntity1.setName("Sistema de Gestión de Inventarios");
        jpaProjectEntity1.setSummary("Gestión de inventario en tiempo real");
        jpaProjectEntity1.setGoals("Gestionar productos, ordenar listas");
        jpaProjectEntity1.setDescription("Es un proyecto para controlar inventarios de manera eficiente");
        jpaProjectEntity1.setMaxtimeMonths(6);
        jpaProjectEntity1.setDate(LocalDate.parse("2024-05-04", formatter));
        jpaProjectEntity1.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity1.setComments("Proyecto inicial");
        projectRepository.save(jpaProjectEntity1);

        // Proyecto 2
        JpaProjectEntity jpaProjectEntity2 = new JpaProjectEntity();
        jpaProjectEntity2.setBudget(5000000);
        jpaProjectEntity2.setName("Plataforma de E-learning");
        jpaProjectEntity2.setSummary("Plataforma para cursos en línea");
        jpaProjectEntity2.setGoals("Facilitar el aprendizaje remoto");
        jpaProjectEntity2.setDescription("Ofrecer cursos interactivos y seguimiento del progreso");
        jpaProjectEntity2.setMaxtimeMonths(12);
        jpaProjectEntity2.setDate(LocalDate.parse("2024-08-01", formatter));
        jpaProjectEntity2.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity2.setComments("En revisión técnica");
        projectRepository.save(jpaProjectEntity2);

        // Proyecto 3
        JpaProjectEntity jpaProjectEntity3 = new JpaProjectEntity();
        jpaProjectEntity3.setBudget(2000000);
        jpaProjectEntity3.setName("Aplicación de Reservas de Restaurantes");
        jpaProjectEntity3.setSummary("Sistema para reservar mesas en restaurantes");
        jpaProjectEntity3.setGoals("Automatizar reservas");
        jpaProjectEntity3.setDescription("Permitir a los usuarios reservar mesas en línea");
        jpaProjectEntity3.setMaxtimeMonths(4);
        jpaProjectEntity3.setDate(LocalDate.parse("2024-12-15", formatter));
        jpaProjectEntity3.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity3.setComments("Proyecto pequeño");
        projectRepository.save(jpaProjectEntity3);

        // Proyecto 4
        JpaProjectEntity jpaProjectEntity4 = new JpaProjectEntity();
        jpaProjectEntity4.setBudget(4000000);
        jpaProjectEntity4.setName("Sistema de Gestión de Recursos Humanos");
        jpaProjectEntity4.setSummary("Gestión de empleados y nóminas");
        jpaProjectEntity4.setGoals("Automatizar procesos de RRHH");
        jpaProjectEntity4.setDescription("Facilitar la gestión de personal y pagos");
        jpaProjectEntity4.setMaxtimeMonths(8);
        jpaProjectEntity4.setDate(LocalDate.parse("2024-08-10", formatter));
        jpaProjectEntity4.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity4.setComments("En desarrollo");
        projectRepository.save(jpaProjectEntity4);

        // Proyecto 5
        JpaProjectEntity jpaProjectEntity5 = new JpaProjectEntity();
        jpaProjectEntity5.setBudget(7500000);
        jpaProjectEntity5.setName("Plataforma de Comercio Electrónico");
        jpaProjectEntity5.setSummary("Ventas en línea de productos");
        jpaProjectEntity5.setGoals("Facilitar las ventas en línea");
        jpaProjectEntity5.setDescription("Ofrecer a los vendedores una manera fácil de vender sus productos");
        jpaProjectEntity5.setMaxtimeMonths(18);
        jpaProjectEntity5.setDate(LocalDate.parse("2024-09-05", formatter));
        jpaProjectEntity5.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity5.setComments("Proyecto grande");
        projectRepository.save(jpaProjectEntity5);

        // Proyecto 6
        JpaProjectEntity jpaProjectEntity6 = new JpaProjectEntity();
        jpaProjectEntity6.setBudget(1500000);
        jpaProjectEntity6.setName("Aplicación de Gestión de Tareas");
        jpaProjectEntity6.setSummary("Organización de tareas diarias");
        jpaProjectEntity6.setGoals("Optimizar la gestión de tareas");
        jpaProjectEntity6.setDescription("Permitir a los usuarios organizar sus tareas de manera eficiente");
        jpaProjectEntity6.setMaxtimeMonths(6);
        jpaProjectEntity6.setDate(LocalDate.parse("2024-10-01", formatter));
        jpaProjectEntity6.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity6.setComments("Proyecto completado con éxito");
        projectRepository.save(jpaProjectEntity6);

        // Proyecto 7
        JpaProjectEntity jpaProjectEntity7 = new JpaProjectEntity();
        jpaProjectEntity7.setBudget(3000000);
        jpaProjectEntity7.setName("Sistema de Gestión de Biblioteca");
        jpaProjectEntity7.setSummary("Control de libros y préstamos");
        jpaProjectEntity7.setGoals("Automatizar procesos de biblioteca");
        jpaProjectEntity7.setDescription("Facilitar la gestión de libros y préstamos");
        jpaProjectEntity7.setMaxtimeMonths(9);
        jpaProjectEntity7.setDate(LocalDate.parse("2024-11-15", formatter));
        jpaProjectEntity7.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity7.setComments("En fase de implementación");
        projectRepository.save(jpaProjectEntity7);

        // Proyecto 8
        JpaProjectEntity jpaProjectEntity8 = new JpaProjectEntity();
        jpaProjectEntity8.setBudget(6000000);
        jpaProjectEntity8.setName("Plataforma de Gestión de Eventos");
        jpaProjectEntity8.setSummary("Organización y gestión de eventos");
        jpaProjectEntity8.setGoals("Facilitar la organización de eventos");
        jpaProjectEntity8.setDescription("Permitir a los organizadores gestionar eventos de manera eficiente");
        jpaProjectEntity8.setMaxtimeMonths(12);
        jpaProjectEntity8.setDate(LocalDate.parse("2024-12-10", formatter));
        jpaProjectEntity8.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity8.setComments("En espera de inicio");
        projectRepository.save(jpaProjectEntity8);

        // Proyecto 9
        JpaProjectEntity jpaProjectEntity9 = new JpaProjectEntity();
        jpaProjectEntity9.setBudget(2500000);
        jpaProjectEntity9.setName("Aplicación de Monitoreo de Salud");
        jpaProjectEntity9.setSummary("Seguimiento de salud en tiempo real");
        jpaProjectEntity9.setGoals("Mejorar el monitoreo de la salud");
        jpaProjectEntity9.setDescription("Permitir a los usuarios monitorear su salud desde cualquier lugar");
        jpaProjectEntity9.setMaxtimeMonths(6);
        jpaProjectEntity9.setDate(LocalDate.parse("2025-01-05", formatter));
        jpaProjectEntity9.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity9.setComments("Proyecto finalizado");
        projectRepository.save(jpaProjectEntity9);
        // Proyecto 10
        JpaProjectEntity jpaProjectEntity10 = new JpaProjectEntity();
        jpaProjectEntity10.setBudget(4500000);
        jpaProjectEntity10.setName("Sistema de Gestión de Transporte");
        jpaProjectEntity10.setSummary("Control de transporte público");
        jpaProjectEntity10.setGoals("Optimizar el transporte público");
        jpaProjectEntity10.setDescription("Facilitar la gestión de rutas y horarios");
        jpaProjectEntity10.setMaxtimeMonths(12);
        jpaProjectEntity10.setDate(LocalDate.parse("2025-02-01", formatter));
        jpaProjectEntity10.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity10.setComments("En desarrollo activo");
        projectRepository.save(jpaProjectEntity10);

        // Continuar con los demás proyectos...
        // Proyecto 11
        JpaProjectEntity jpaProjectEntity11 = new JpaProjectEntity();
        jpaProjectEntity11.setBudget(7000000);
        jpaProjectEntity11.setName("Plataforma de Gestión de Contenidos");
        jpaProjectEntity11.setSummary("Gestión de contenidos digitales");
        jpaProjectEntity11.setGoals("Facilitar la gestión de contenidos");
        jpaProjectEntity11.setDescription("Permitir a los usuarios gestionar sus contenidos digitales");
        jpaProjectEntity11.setMaxtimeMonths(18);
        jpaProjectEntity11.setDate(LocalDate.parse("2025-03-15", formatter));
        jpaProjectEntity11.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity11.setComments("En fase de planificación");
        projectRepository.save(jpaProjectEntity11);

        // Proyecto 12
        JpaProjectEntity jpaProjectEntity12 = new JpaProjectEntity();
        jpaProjectEntity12.setBudget(2000000);
        jpaProjectEntity12.setName("Aplicación de Gestión de Finanzas");
        jpaProjectEntity12.setSummary("Control de finanzas personales");
        jpaProjectEntity12.setGoals("Optimizar la gestión de finanzas");
        jpaProjectEntity12.setDescription("Permitir a los usuarios gestionar sus finanzas de manera eficiente");
        jpaProjectEntity12.setMaxtimeMonths(6);
        jpaProjectEntity12.setDate(LocalDate.parse("2025-04-10", formatter));
        jpaProjectEntity12.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity12.setComments("Proyecto exitoso");
        projectRepository.save(jpaProjectEntity12);

        // Proyecto 13
        JpaProjectEntity jpaProjectEntity13 = new JpaProjectEntity();
        jpaProjectEntity13.setBudget(3500000);
        jpaProjectEntity13.setName("Sistema de Gestión de Almacenes");
        jpaProjectEntity13.setSummary("Control de inventarios en almacenes");
        jpaProjectEntity13.setGoals("Automatizar procesos de almacén");
        jpaProjectEntity13.setDescription("Facilitar la gestión de inventarios en almacenes");
        jpaProjectEntity13.setMaxtimeMonths(9);
        jpaProjectEntity13.setDate(LocalDate.parse("2025-05-05", formatter));
        jpaProjectEntity13.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity13.setComments("Implementación en curso");
        projectRepository.save(jpaProjectEntity13);

        // Proyecto 14
        JpaProjectEntity jpaProjectEntity14 = new JpaProjectEntity();
        jpaProjectEntity14.setBudget(5500000);
        jpaProjectEntity14.setName("Plataforma de Gestión de Redes Sociales");
        jpaProjectEntity14.setSummary("Gestión de redes sociales");
        jpaProjectEntity14.setGoals("Facilitar la gestión de redes sociales");
        jpaProjectEntity14.setDescription("Permitir a los usuarios gestionar sus redes sociales de manera eficiente");
        jpaProjectEntity14.setMaxtimeMonths(12);
        jpaProjectEntity14.setDate(LocalDate.parse("2025-06-01", formatter));
        jpaProjectEntity14.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity14.setComments("En espera de recursos");
        projectRepository.save(jpaProjectEntity14);

        // Proyecto 15
        JpaProjectEntity jpaProjectEntity15 = new JpaProjectEntity();
        jpaProjectEntity15.setBudget(3000000);
        jpaProjectEntity15.setName("Aplicación de Gestión de Viajes");
        jpaProjectEntity15.setSummary("Planificación y gestión de viajes");
        jpaProjectEntity15.setGoals("Optimizar la gestión de viajes");
        jpaProjectEntity15.setDescription("Permitir a los usuarios planificar y gestionar sus viajes");
        jpaProjectEntity15.setMaxtimeMonths(6);
        jpaProjectEntity15.setDate(LocalDate.parse("2025-07-15", formatter));
        jpaProjectEntity15.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity15.setComments("Proyecto completado");
        projectRepository.save(jpaProjectEntity15);

        // Proyecto 16
        JpaProjectEntity jpaProjectEntity16 = new JpaProjectEntity();
        jpaProjectEntity16.setBudget(5000000);
        jpaProjectEntity16.setName("Sistema de Gestión de Clientes");
        jpaProjectEntity16.setSummary("Gestión de relaciones con clientes");
        jpaProjectEntity16.setGoals("Automatizar procesos de CRM");
        jpaProjectEntity16.setDescription("Facilitar la gestión de clientes");
        jpaProjectEntity16.setMaxtimeMonths(12);
        jpaProjectEntity16.setDate(LocalDate.parse("2025-08-10", formatter));
        jpaProjectEntity16.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity16.setComments("Fase de desarrollo avanzada");
        projectRepository.save(jpaProjectEntity16);

        // Proyecto 17
        JpaProjectEntity jpaProjectEntity17 = new JpaProjectEntity();
        jpaProjectEntity17.setBudget(8000000);
        jpaProjectEntity17.setName("Plataforma de Gestión de Aprendizaje");
        jpaProjectEntity17.setSummary("Gestión de aprendizaje personalizado");
        jpaProjectEntity17.setGoals("Facilitar el aprendizaje personalizado");
        jpaProjectEntity17.setDescription("Permitir a los usuarios gestionar su aprendizaje de manera eficiente");
        jpaProjectEntity17.setMaxtimeMonths(18);
        jpaProjectEntity17.setDate(LocalDate.parse("2025-09-05", formatter));
        jpaProjectEntity17.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity17.setComments("Proyecto estratégico");
        projectRepository.save(jpaProjectEntity17);

        // Proyecto 18
        JpaProjectEntity jpaProjectEntity18 = new JpaProjectEntity();
        jpaProjectEntity18.setBudget(2500000);
        jpaProjectEntity18.setName("Aplicación de Seguridad en el Hogar");
        jpaProjectEntity18.setSummary("Monitoreo de seguridad en el hogar");
        jpaProjectEntity18.setGoals("Mejorar la seguridad en el hogar");
        jpaProjectEntity18.setDescription("Permitir a los usuarios monitorear sus hogares desde cualquier lugar");
        jpaProjectEntity18.setMaxtimeMonths(6);
        jpaProjectEntity18.setDate(LocalDate.parse("2025-10-01", formatter));
        jpaProjectEntity18.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity18.setComments("Implementación exitosa");
        projectRepository.save(jpaProjectEntity18);

        // Proyecto 19
        JpaProjectEntity jpaProjectEntity19 = new JpaProjectEntity();
        jpaProjectEntity19.setBudget(6000000);
        jpaProjectEntity19.setName("Sistema de Gestión de Proyectos");
        jpaProjectEntity19.setSummary("Gestión colaborativa de proyectos");
        jpaProjectEntity19.setGoals("Facilitar la gestión de proyectos");
        jpaProjectEntity19.setDescription("Permitir a los equipos gestionar proyectos de manera colaborativa");
        jpaProjectEntity19.setMaxtimeMonths(12);
        jpaProjectEntity19.setDate(LocalDate.parse("2025-11-15", formatter));
        jpaProjectEntity19.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity19.setComments("En desarrollo");
        projectRepository.save(jpaProjectEntity19);

        // Proyecto 20
        JpaProjectEntity jpaProjectEntity20 = new JpaProjectEntity();
        jpaProjectEntity20.setBudget(4000000);
        jpaProjectEntity20.setName("Plataforma de Gestión de Contratos");
        jpaProjectEntity20.setSummary("Gestión de contratos digitales");
        jpaProjectEntity20.setGoals("Automatizar la gestión de contratos");
        jpaProjectEntity20.setDescription("Facilitar la creación y gestión de contratos digitales");
        jpaProjectEntity20.setMaxtimeMonths(9);
        jpaProjectEntity20.setDate(LocalDate.parse("2025-12-10", formatter));
        jpaProjectEntity20.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity20.setComments("En fase de diseño");
        projectRepository.save(jpaProjectEntity20);

        // Proyecto 21
        JpaProjectEntity jpaProjectEntity21 = new JpaProjectEntity();
        jpaProjectEntity21.setBudget(5000000);
        jpaProjectEntity21.setName("Sistema de Gestión de Inventarios Avanzado");
        jpaProjectEntity21.setSummary("Gestión de inventario en tiempo real con IA");
        jpaProjectEntity21.setGoals("Optimizar la gestión de inventarios con IA");
        jpaProjectEntity21.setDescription("Es un proyecto para controlar inventarios de manera eficiente usando IA");
        jpaProjectEntity21.setMaxtimeMonths(12);
        jpaProjectEntity21.setDate(LocalDate.parse("2026-01-05", formatter));
        jpaProjectEntity21.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity21.setComments("Propuesta innovadora");
        projectRepository.save(jpaProjectEntity21);

        // Proyecto 22
        JpaProjectEntity jpaProjectEntity22 = new JpaProjectEntity();
        jpaProjectEntity22.setBudget(7500000);
        jpaProjectEntity22.setName("Plataforma de E-learning Avanzada");
        jpaProjectEntity22.setSummary("Plataforma para cursos en línea con IA");
        jpaProjectEntity22.setGoals("Facilitar el aprendizaje remoto con IA");
        jpaProjectEntity22.setDescription("Ofrecer cursos interactivos y seguimiento del progreso con IA");
        jpaProjectEntity22.setMaxtimeMonths(18);
        jpaProjectEntity22.setDate(LocalDate.parse("2026-02-01", formatter));
        jpaProjectEntity22.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity22.setComments("Proyecto con IA");
        projectRepository.save(jpaProjectEntity22);

        // Proyecto 23
        JpaProjectEntity jpaProjectEntity23 = new JpaProjectEntity();
        jpaProjectEntity23.setBudget(3000000);
        jpaProjectEntity23.setName("Aplicación de Reservas de Hoteles");
        jpaProjectEntity23.setSummary("Sistema para reservar habitaciones en hoteles");
        jpaProjectEntity23.setGoals("Automatizar reservas de hoteles");
        jpaProjectEntity23.setDescription("Permitir a los usuarios reservar habitaciones en línea");
        jpaProjectEntity23.setMaxtimeMonths(6);
        jpaProjectEntity23.setDate(LocalDate.parse("2026-03-15", formatter));
        jpaProjectEntity23.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity23.setComments("Nueva propuesta");
        projectRepository.save(jpaProjectEntity23);

        // Proyecto 24
        JpaProjectEntity jpaProjectEntity24 = new JpaProjectEntity();
        jpaProjectEntity24.setBudget(6000000);
        jpaProjectEntity24.setName("Sistema de Gestión de Recursos Humanos Avanzado");
        jpaProjectEntity24.setSummary("Gestión de empleados y nóminas con IA");
        jpaProjectEntity24.setGoals("Automatizar procesos de RRHH con IA");
        jpaProjectEntity24.setDescription("Facilitar la gestión de personal y pagos con IA");
        jpaProjectEntity24.setMaxtimeMonths(12);
        jpaProjectEntity24.setDate(LocalDate.parse("2026-04-10", formatter));
        jpaProjectEntity24.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity24.setComments("Implementando IA");
        projectRepository.save(jpaProjectEntity24);

        // Proyecto 25
        JpaProjectEntity jpaProjectEntity25 = new JpaProjectEntity();
        jpaProjectEntity25.setBudget(10000000);
        jpaProjectEntity25.setName("Plataforma de Comercio Electrónico Avanzada");
        jpaProjectEntity25.setSummary("Ventas en línea de productos con IA");
        jpaProjectEntity25.setGoals("Facilitar las ventas en línea con IA");
        jpaProjectEntity25.setDescription("Ofrecer a los vendedores una manera fácil de vender sus productos con IA");
        jpaProjectEntity25.setMaxtimeMonths(24);
        jpaProjectEntity25.setDate(LocalDate.parse("2026-05-05", formatter));
        jpaProjectEntity25.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity25.setComments("Proyecto grande con IA");
        projectRepository.save(jpaProjectEntity25);

        // Proyecto 26
        JpaProjectEntity jpaProjectEntity26 = new JpaProjectEntity();
        jpaProjectEntity26.setBudget(2500000);
        jpaProjectEntity26.setName("Aplicación de Gestión de Tareas Avanzada");
        jpaProjectEntity26.setSummary("Organización de tareas diarias con IA");
        jpaProjectEntity26.setGoals("Optimizar la gestión de tareas con IA");
        jpaProjectEntity26.setDescription("Permitir a los usuarios organizar sus tareas de manera eficiente con IA");
        jpaProjectEntity26.setMaxtimeMonths(9);
        jpaProjectEntity26.setDate(LocalDate.parse("2026-06-01", formatter));
        jpaProjectEntity26.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity26.setComments("Proyecto IA completado");
        projectRepository.save(jpaProjectEntity26);

        // Proyecto 27
        JpaProjectEntity jpaProjectEntity27 = new JpaProjectEntity();
        jpaProjectEntity27.setBudget(4000000);
        jpaProjectEntity27.setName("Sistema de Gestión de Biblioteca Avanzado");
        jpaProjectEntity27.setSummary("Control de libros y préstamos con IA");
        jpaProjectEntity27.setGoals("Automatizar procesos de biblioteca con IA");
        jpaProjectEntity27.setDescription("Facilitar la gestión de libros y préstamos con IA");
        jpaProjectEntity27.setMaxtimeMonths(12);
        jpaProjectEntity27.setDate(LocalDate.parse("2026-07-15", formatter));
        jpaProjectEntity27.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity27.setComments("Implementando IA en biblioteca");
        projectRepository.save(jpaProjectEntity27);

        // Proyecto 28
        JpaProjectEntity jpaProjectEntity28 = new JpaProjectEntity();
        jpaProjectEntity28.setBudget(8000000);
        jpaProjectEntity28.setName("Plataforma de Gestión de Eventos Avanzada");
        jpaProjectEntity28.setSummary("Organización y gestión de eventos con IA");
        jpaProjectEntity28.setGoals("Facilitar la organización de eventos con IA");
        jpaProjectEntity28.setDescription("Permitir a los organizadores gestionar eventos de manera eficiente con IA");
        jpaProjectEntity28.setMaxtimeMonths(18);
        jpaProjectEntity28.setDate(LocalDate.parse("2026-08-10", formatter));
        jpaProjectEntity28.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity28.setComments("Proyecto IA para eventos");
        projectRepository.save(jpaProjectEntity28);

        // Proyecto 29
        JpaProjectEntity jpaProjectEntity29 = new JpaProjectEntity();
        jpaProjectEntity29.setBudget(3500000);
        jpaProjectEntity29.setName("Aplicación de Monitoreo de Salud Avanzada");
        jpaProjectEntity29.setSummary("Seguimiento de salud en tiempo real con IA");
        jpaProjectEntity29.setGoals("Mejorar el monitoreo de la salud con IA");
        jpaProjectEntity29.setDescription("Permitir a los usuarios monitorear su salud desde cualquier lugar con IA");
        jpaProjectEntity29.setMaxtimeMonths(12);
        jpaProjectEntity29.setDate(LocalDate.parse("2026-09-05", formatter));
        jpaProjectEntity29.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity29.setComments("Salud con IA completado");
        projectRepository.save(jpaProjectEntity29);

        // Proyecto 30
        JpaProjectEntity jpaProjectEntity30 = new JpaProjectEntity();
        jpaProjectEntity30.setBudget(6000000);
        jpaProjectEntity30.setName("Sistema de Gestión de Transporte Avanzado");
        jpaProjectEntity30.setSummary("Control de transporte público con IA");
        jpaProjectEntity30.setGoals("Optimizar el transporte público con IA");
        jpaProjectEntity30.setDescription("Facilitar la gestión de rutas y horarios con IA");
        jpaProjectEntity30.setMaxtimeMonths(18);
        jpaProjectEntity30.setDate(LocalDate.parse("2026-10-01", formatter));
        jpaProjectEntity30.setStatus(JpaProjectStatusEntity.IN_PROGRESS);
        jpaProjectEntity30.setComments("Transporte inteligente en desarrollo");
        projectRepository.save(jpaProjectEntity30);


    }
}