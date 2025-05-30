package com.unicauca.CompanyService.repository;

import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.entity.ProjectStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ProjectDataLoader implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    public ProjectDataLoader(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Proyecto 1
        Project project1 = new Project();
        project1.setBudget(3500000);
        project1.setName("Sistema de Gestión de Inventarios");
        project1.setSummary("Gestión de inventario en tiempo real");
        project1.setGoals("Gestionar productos, ordenar listas");
        project1.setDescription("Es un proyecto para controlar inventarios de manera eficiente");
        project1.setMaxtimeMonths(6);
        project1.setDate(LocalDate.parse("2024-05-04", formatter));
        project1.setStatus(ProjectStatus.ACCEPTED);
        project1.setComments("Proyecto inicial");
        projectRepository.save(project1);

        // Proyecto 2
        Project project2 = new Project();
        project2.setBudget(5000000);
        project2.setName("Plataforma de E-learning");
        project2.setSummary("Plataforma para cursos en línea");
        project2.setGoals("Facilitar el aprendizaje remoto");
        project2.setDescription("Ofrecer cursos interactivos y seguimiento del progreso");
        project2.setMaxtimeMonths(12);
        project2.setDate(LocalDate.parse("2024-08-01", formatter));
        project2.setStatus(ProjectStatus.CLOSED);
        project2.setComments("En revisión técnica");
        projectRepository.save(project2);

        // Proyecto 3
        Project project3 = new Project();
        project3.setBudget(2000000);
        project3.setName("Aplicación de Reservas de Restaurantes");
        project3.setSummary("Sistema para reservar mesas en restaurantes");
        project3.setGoals("Automatizar reservas");
        project3.setDescription("Permitir a los usuarios reservar mesas en línea");
        project3.setMaxtimeMonths(4);
        project3.setDate(LocalDate.parse("2024-12-15", formatter));
        project3.setStatus(ProjectStatus.IN_PROGRESS);
        project3.setComments("Proyecto pequeño");
        projectRepository.save(project3);

        // Proyecto 4
        Project project4 = new Project();
        project4.setBudget(4000000);
        project4.setName("Sistema de Gestión de Recursos Humanos");
        project4.setSummary("Gestión de empleados y nóminas");
        project4.setGoals("Automatizar procesos de RRHH");
        project4.setDescription("Facilitar la gestión de personal y pagos");
        project4.setMaxtimeMonths(8);
        project4.setDate(LocalDate.parse("2024-08-10", formatter));
        project4.setStatus(ProjectStatus.RECEIVED);
        project4.setComments("En desarrollo");
        projectRepository.save(project4);

        // Proyecto 5
        Project project5 = new Project();
        project5.setBudget(7500000);
        project5.setName("Plataforma de Comercio Electrónico");
        project5.setSummary("Ventas en línea de productos");
        project5.setGoals("Facilitar las ventas en línea");
        project5.setDescription("Ofrecer a los vendedores una manera fácil de vender sus productos");
        project5.setMaxtimeMonths(18);
        project5.setDate(LocalDate.parse("2024-09-05", formatter));
        project5.setStatus(ProjectStatus.REJECTED);
        project5.setComments("Proyecto grande");
        projectRepository.save(project5);

        // Proyecto 6
        Project project6 = new Project();
        project6.setBudget(1500000);
        project6.setName("Aplicación de Gestión de Tareas");
        project6.setSummary("Organización de tareas diarias");
        project6.setGoals("Optimizar la gestión de tareas");
        project6.setDescription("Permitir a los usuarios organizar sus tareas de manera eficiente");
        project6.setMaxtimeMonths(6);
        project6.setDate(LocalDate.parse("2024-10-01", formatter));
        project6.setStatus(ProjectStatus.IN_PROGRESS);
        project6.setComments("Proyecto completado con éxito");
        projectRepository.save(project6);

        // Proyecto 7
        Project project7 = new Project();
        project7.setBudget(3000000);
        project7.setName("Sistema de Gestión de Biblioteca");
        project7.setSummary("Control de libros y préstamos");
        project7.setGoals("Automatizar procesos de biblioteca");
        project7.setDescription("Facilitar la gestión de libros y préstamos");
        project7.setMaxtimeMonths(9);
        project7.setDate(LocalDate.parse("2024-11-15", formatter));
        project7.setStatus(ProjectStatus.IN_PROGRESS);
        project7.setComments("En fase de implementación");
        projectRepository.save(project7);

        // Proyecto 8
        Project project8 = new Project();
        project8.setBudget(6000000);
        project8.setName("Plataforma de Gestión de Eventos");
        project8.setSummary("Organización y gestión de eventos");
        project8.setGoals("Facilitar la organización de eventos");
        project8.setDescription("Permitir a los organizadores gestionar eventos de manera eficiente");
        project8.setMaxtimeMonths(12);
        project8.setDate(LocalDate.parse("2024-12-10", formatter));
        project8.setStatus(ProjectStatus.IN_PROGRESS);
        project8.setComments("En espera de inicio");
        projectRepository.save(project8);

        // Proyecto 9
        Project project9 = new Project();
        project9.setBudget(2500000);
        project9.setName("Aplicación de Monitoreo de Salud");
        project9.setSummary("Seguimiento de salud en tiempo real");
        project9.setGoals("Mejorar el monitoreo de la salud");
        project9.setDescription("Permitir a los usuarios monitorear su salud desde cualquier lugar");
        project9.setMaxtimeMonths(6);
        project9.setDate(LocalDate.parse("2025-01-05", formatter));
        project9.setStatus(ProjectStatus.IN_PROGRESS);
        project9.setComments("Proyecto finalizado");
        projectRepository.save(project9);

        // Proyecto 10
        Project project10 = new Project();
        project10.setBudget(4500000);
        project10.setName("Sistema de Gestión de Transporte");
        project10.setSummary("Control de transporte público");
        project10.setGoals("Optimizar el transporte público");
        project10.setDescription("Facilitar la gestión de rutas y horarios");
        project10.setMaxtimeMonths(12);
        project10.setDate(LocalDate.parse("2025-02-01", formatter));
        project10.setStatus(ProjectStatus.IN_PROGRESS);
        project10.setComments("En desarrollo activo");
        projectRepository.save(project10);

        // Continuar con los demás proyectos...
        // Proyecto 11
        Project project11 = new Project();
        project11.setBudget(7000000);
        project11.setName("Plataforma de Gestión de Contenidos");
        project11.setSummary("Gestión de contenidos digitales");
        project11.setGoals("Facilitar la gestión de contenidos");
        project11.setDescription("Permitir a los usuarios gestionar sus contenidos digitales");
        project11.setMaxtimeMonths(18);
        project11.setDate(LocalDate.parse("2025-03-15", formatter));
        project11.setStatus(ProjectStatus.IN_PROGRESS);
        project11.setComments("En fase de planificación");
        projectRepository.save(project11);

        // Proyecto 12
        Project project12 = new Project();
        project12.setBudget(2000000);
        project12.setName("Aplicación de Gestión de Finanzas");
        project12.setSummary("Control de finanzas personales");
        project12.setGoals("Optimizar la gestión de finanzas");
        project12.setDescription("Permitir a los usuarios gestionar sus finanzas de manera eficiente");
        project12.setMaxtimeMonths(6);
        project12.setDate(LocalDate.parse("2025-04-10", formatter));
        project12.setStatus(ProjectStatus.IN_PROGRESS);
        project12.setComments("Proyecto exitoso");
        projectRepository.save(project12);

        // Proyecto 13
        Project project13 = new Project();
        project13.setBudget(3500000);
        project13.setName("Sistema de Gestión de Almacenes");
        project13.setSummary("Control de inventarios en almacenes");
        project13.setGoals("Automatizar procesos de almacén");
        project13.setDescription("Facilitar la gestión de inventarios en almacenes");
        project13.setMaxtimeMonths(9);
        project13.setDate(LocalDate.parse("2025-05-05", formatter));
        project13.setStatus(ProjectStatus.IN_PROGRESS);
        project13.setComments("Implementación en curso");
        projectRepository.save(project13);

        // Proyecto 14
        Project project14 = new Project();
        project14.setBudget(5500000);
        project14.setName("Plataforma de Gestión de Redes Sociales");
        project14.setSummary("Gestión de redes sociales");
        project14.setGoals("Facilitar la gestión de redes sociales");
        project14.setDescription("Permitir a los usuarios gestionar sus redes sociales de manera eficiente");
        project14.setMaxtimeMonths(12);
        project14.setDate(LocalDate.parse("2025-06-01", formatter));
        project14.setStatus(ProjectStatus.IN_PROGRESS);
        project14.setComments("En espera de recursos");
        projectRepository.save(project14);

        // Proyecto 15
        Project project15 = new Project();
        project15.setBudget(3000000);
        project15.setName("Aplicación de Gestión de Viajes");
        project15.setSummary("Planificación y gestión de viajes");
        project15.setGoals("Optimizar la gestión de viajes");
        project15.setDescription("Permitir a los usuarios planificar y gestionar sus viajes");
        project15.setMaxtimeMonths(6);
        project15.setDate(LocalDate.parse("2025-07-15", formatter));
        project15.setStatus(ProjectStatus.IN_PROGRESS);
        project15.setComments("Proyecto completado");
        projectRepository.save(project15);

        // Proyecto 16
        Project project16 = new Project();
        project16.setBudget(5000000);
        project16.setName("Sistema de Gestión de Clientes");
        project16.setSummary("Gestión de relaciones con clientes");
        project16.setGoals("Automatizar procesos de CRM");
        project16.setDescription("Facilitar la gestión de clientes");
        project16.setMaxtimeMonths(12);
        project16.setDate(LocalDate.parse("2025-08-10", formatter));
        project16.setStatus(ProjectStatus.IN_PROGRESS);
        project16.setComments("Fase de desarrollo avanzada");
        projectRepository.save(project16);

        // Proyecto 17
        Project project17 = new Project();
        project17.setBudget(8000000);
        project17.setName("Plataforma de Gestión de Aprendizaje");
        project17.setSummary("Gestión de aprendizaje personalizado");
        project17.setGoals("Facilitar el aprendizaje personalizado");
        project17.setDescription("Permitir a los usuarios gestionar su aprendizaje de manera eficiente");
        project17.setMaxtimeMonths(18);
        project17.setDate(LocalDate.parse("2025-09-05", formatter));
        project17.setStatus(ProjectStatus.IN_PROGRESS);
        project17.setComments("Proyecto estratégico");
        projectRepository.save(project17);

        // Proyecto 18
        Project project18 = new Project();
        project18.setBudget(2500000);
        project18.setName("Aplicación de Seguridad en el Hogar");
        project18.setSummary("Monitoreo de seguridad en el hogar");
        project18.setGoals("Mejorar la seguridad en el hogar");
        project18.setDescription("Permitir a los usuarios monitorear sus hogares desde cualquier lugar");
        project18.setMaxtimeMonths(6);
        project18.setDate(LocalDate.parse("2025-10-01", formatter));
        project18.setStatus(ProjectStatus.IN_PROGRESS);
        project18.setComments("Implementación exitosa");
        projectRepository.save(project18);

        // Proyecto 19
        Project project19 = new Project();
        project19.setBudget(6000000);
        project19.setName("Sistema de Gestión de Proyectos");
        project19.setSummary("Gestión colaborativa de proyectos");
        project19.setGoals("Facilitar la gestión de proyectos");
        project19.setDescription("Permitir a los equipos gestionar proyectos de manera colaborativa");
        project19.setMaxtimeMonths(12);
        project19.setDate(LocalDate.parse("2025-11-15", formatter));
        project19.setStatus(ProjectStatus.IN_PROGRESS);
        project19.setComments("En desarrollo");
        projectRepository.save(project19);

        // Proyecto 20
        Project project20 = new Project();
        project20.setBudget(4000000);
        project20.setName("Plataforma de Gestión de Contratos");
        project20.setSummary("Gestión de contratos digitales");
        project20.setGoals("Automatizar la gestión de contratos");
        project20.setDescription("Facilitar la creación y gestión de contratos digitales");
        project20.setMaxtimeMonths(9);
        project20.setDate(LocalDate.parse("2025-12-10", formatter));
        project20.setStatus(ProjectStatus.IN_PROGRESS);
        project20.setComments("En fase de diseño");
        projectRepository.save(project20);

        // Proyecto 21
        Project project21 = new Project();
        project21.setBudget(5000000);
        project21.setName("Sistema de Gestión de Inventarios Avanzado");
        project21.setSummary("Gestión de inventario en tiempo real con IA");
        project21.setGoals("Optimizar la gestión de inventarios con IA");
        project21.setDescription("Es un proyecto para controlar inventarios de manera eficiente usando IA");
        project21.setMaxtimeMonths(12);
        project21.setDate(LocalDate.parse("2026-01-05", formatter));
        project21.setStatus(ProjectStatus.IN_PROGRESS);
        project21.setComments("Propuesta innovadora");
        projectRepository.save(project21);

        // Proyecto 22
        Project project22 = new Project();
        project22.setBudget(7500000);
        project22.setName("Plataforma de E-learning Avanzada");
        project22.setSummary("Plataforma para cursos en línea con IA");
        project22.setGoals("Facilitar el aprendizaje remoto con IA");
        project22.setDescription("Ofrecer cursos interactivos y seguimiento del progreso con IA");
        project22.setMaxtimeMonths(18);
        project22.setDate(LocalDate.parse("2026-02-01", formatter));
        project22.setStatus(ProjectStatus.IN_PROGRESS);
        project22.setComments("Proyecto con IA");
        projectRepository.save(project22);

        // Proyecto 23
        Project project23 = new Project();
        project23.setBudget(3000000);
        project23.setName("Aplicación de Reservas de Hoteles");
        project23.setSummary("Sistema para reservar habitaciones en hoteles");
        project23.setGoals("Automatizar reservas de hoteles");
        project23.setDescription("Permitir a los usuarios reservar habitaciones en línea");
        project23.setMaxtimeMonths(6);
        project23.setDate(LocalDate.parse("2026-03-15", formatter));
        project23.setStatus(ProjectStatus.IN_PROGRESS);
        project23.setComments("Nueva propuesta");
        projectRepository.save(project23);

        // Proyecto 24
        Project project24 = new Project();
        project24.setBudget(6000000);
        project24.setName("Sistema de Gestión de Recursos Humanos Avanzado");
        project24.setSummary("Gestión de empleados y nóminas con IA");
        project24.setGoals("Automatizar procesos de RRHH con IA");
        project24.setDescription("Facilitar la gestión de personal y pagos con IA");
        project24.setMaxtimeMonths(12);
        project24.setDate(LocalDate.parse("2026-04-10", formatter));
        project24.setStatus(ProjectStatus.IN_PROGRESS);
        project24.setComments("Implementando IA");
        projectRepository.save(project24);

        // Proyecto 25
        Project project25 = new Project();
        project25.setBudget(10000000);
        project25.setName("Plataforma de Comercio Electrónico Avanzada");
        project25.setSummary("Ventas en línea de productos con IA");
        project25.setGoals("Facilitar las ventas en línea con IA");
        project25.setDescription("Ofrecer a los vendedores una manera fácil de vender sus productos con IA");
        project25.setMaxtimeMonths(24);
        project25.setDate(LocalDate.parse("2026-05-05", formatter));
        project25.setStatus(ProjectStatus.IN_PROGRESS);
        project25.setComments("Proyecto grande con IA");
        projectRepository.save(project25);

        // Proyecto 26
        Project project26 = new Project();
        project26.setBudget(2500000);
        project26.setName("Aplicación de Gestión de Tareas Avanzada");
        project26.setSummary("Organización de tareas diarias con IA");
        project26.setGoals("Optimizar la gestión de tareas con IA");
        project26.setDescription("Permitir a los usuarios organizar sus tareas de manera eficiente con IA");
        project26.setMaxtimeMonths(9);
        project26.setDate(LocalDate.parse("2026-06-01", formatter));
        project26.setStatus(ProjectStatus.IN_PROGRESS);
        project26.setComments("Proyecto IA completado");
        projectRepository.save(project26);

        // Proyecto 27
        Project project27 = new Project();
        project27.setBudget(4000000);
        project27.setName("Sistema de Gestión de Biblioteca Avanzado");
        project27.setSummary("Control de libros y préstamos con IA");
        project27.setGoals("Automatizar procesos de biblioteca con IA");
        project27.setDescription("Facilitar la gestión de libros y préstamos con IA");
        project27.setMaxtimeMonths(12);
        project27.setDate(LocalDate.parse("2026-07-15", formatter));
        project27.setStatus(ProjectStatus.IN_PROGRESS);
        project27.setComments("Implementando IA en biblioteca");
        projectRepository.save(project27);

        // Proyecto 28
        Project project28 = new Project();
        project28.setBudget(8000000);
        project28.setName("Plataforma de Gestión de Eventos Avanzada");
        project28.setSummary("Organización y gestión de eventos con IA");
        project28.setGoals("Facilitar la organización de eventos con IA");
        project28.setDescription("Permitir a los organizadores gestionar eventos de manera eficiente con IA");
        project28.setMaxtimeMonths(18);
        project28.setDate(LocalDate.parse("2026-08-10", formatter));
        project28.setStatus(ProjectStatus.IN_PROGRESS);
        project28.setComments("Proyecto IA para eventos");
        projectRepository.save(project28);

        // Proyecto 29
        Project project29 = new Project();
        project29.setBudget(3500000);
        project29.setName("Aplicación de Monitoreo de Salud Avanzada");
        project29.setSummary("Seguimiento de salud en tiempo real con IA");
        project29.setGoals("Mejorar el monitoreo de la salud con IA");
        project29.setDescription("Permitir a los usuarios monitorear su salud desde cualquier lugar con IA");
        project29.setMaxtimeMonths(12);
        project29.setDate(LocalDate.parse("2026-09-05", formatter));
        project29.setStatus(ProjectStatus.IN_PROGRESS);
        project29.setComments("Salud con IA completado");
        projectRepository.save(project29);

        // Proyecto 30
        Project project30 = new Project();
        project30.setBudget(6000000);
        project30.setName("Sistema de Gestión de Transporte Avanzado");
        project30.setSummary("Control de transporte público con IA");
        project30.setGoals("Optimizar el transporte público con IA");
        project30.setDescription("Facilitar la gestión de rutas y horarios con IA");
        project30.setMaxtimeMonths(18);
        project30.setDate(LocalDate.parse("2026-10-01", formatter));
        project30.setStatus(ProjectStatus.IN_PROGRESS);
        project30.setComments("Transporte inteligente en desarrollo");
        projectRepository.save(project30);
    }
}