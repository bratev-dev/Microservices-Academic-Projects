package com.unicauca.CompanyService.controller;

import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // Permite que Postman o front acceda
public class ProjectController {

    @Autowired
    private ProjectService service;

    // Crear proyecto
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return service.createProject(project);
    }

    // Listar todos los proyectos
    @GetMapping
    public List<Project> getAllProjects() {
        return service.getAllProjects();
    }

    // Buscar proyecto por ID
    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable int id) {
        System.out.println("Controller");
        return service.getProjectById(id);
    }

    // Actualizar proyecto
    @PutMapping("/{id}")
    public Optional<Project> updateProject(@PathVariable int id, @RequestBody Project updatedProject) {
        return service.updateProject(id, updatedProject);
    }

    // Eliminar proyecto
    @DeleteMapping("/{id}")
    public boolean deleteProject(@PathVariable int id) {
        return service.deleteProject(id);
    }

    // Aprobar un proyecto
    @PostMapping("/{id}/approve")
    public String approveProject(@PathVariable int id) {
        System.out.println("================================================================================\n" +
                "                                CONTROLLER                                       \n" +
                "================================================================================");
        return service.approveProject(id);
    }

    // Rechazar un proyecto
    @PostMapping("/{id}/reject")
    public String rejectProject(@PathVariable int id) {
    System.out.println("Filtro UNO");
        return service.rejectProject(id);
    }

    // Asignar un proyecto
    @PostMapping("/{id}/assign")
    public String assignProject(@PathVariable int id) {
        return service.assignProject(id);
    }

    // Completar un proyecto
    @PostMapping("/{id}/complete")
    public String completeProject(@PathVariable int id) {
        return service.completeProject(id);
    }

}
