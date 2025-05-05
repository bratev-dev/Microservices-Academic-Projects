package com.unicauca.CompanyService.controller;

import com.unicauca.CompanyService.dto.ProjectDTO;
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
    public Project createProject(@RequestBody ProjectDTO projectDTO) {
        return service.createProject(projectDTO);
    }

    // Listar todos los proyectos
    @GetMapping
    public List<Project> getAllProjects() {
        return service.getAllProjects();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {  // Cambiar a Long
        return service.getProjectById(id);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody ProjectDTO updatedProjectDTO) {  // Cambiar a Long
        return service.updateProject(id, updatedProjectDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProject(@PathVariable Long id) {  // Cambiar a Long
        return service.deleteProject(id);
    }

    @PostMapping("/{id}/approve")
    public String approveProject(@PathVariable Long id) {  // Cambiar a Long
        return service.approveProject(id);
    }

    @PostMapping("/{id}/reject")
    public String rejectProject(@PathVariable Long id) {  // Cambiar a Long
        return service.rejectProject(id);
    }

    @PostMapping("/{id}/assign")
    public String assignProject(@PathVariable Long id) {  // Cambiar a Long
        return service.assignProject(id);
    }

    @PostMapping("/{id}/complete")
    public String completeProject(@PathVariable Long id) {  // Cambiar a Long
        return service.completeProject(id);
    }
    // En ProjectController.java
    @PostMapping("/{id}/markAsReceived") // Cambia la ruta del endpoint
    public String markProjectAsReceived(@PathVariable Long id) {
        return service.markProjectAsReceived(id); // Llama al método renombrado
    }
}
