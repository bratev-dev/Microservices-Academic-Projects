package com.unicauca.CoordinatorService.repository;

import com.unicauca.CoordinatorService.entity.Project;
import com.unicauca.CoordinatorService.entity.ProjectStatus;
import com.unicauca.CoordinatorService.service.ProjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProjectRepository projectRepository;

    public DataLoader(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Project project = new Project();
        project.setName("Sistema de Inventarios");
        project.setSummary("Control de stock para empresas de alimentos");
        project.setGoals("Optimizar inventario");
        project.setDescription("Sistema web responsive para gestionar productos y proveedores.");
        project.setMaxtimeMonths(6);
        project.setDate(LocalDate.now());
        project.setStatus(ProjectStatus.IN_PROGRESS);
        project.setComments("Proyecto prioritario");
        project.setCompanyId(1L);
        project.setAssignedTo(null);

        projectRepository.save(project);
    }
}
