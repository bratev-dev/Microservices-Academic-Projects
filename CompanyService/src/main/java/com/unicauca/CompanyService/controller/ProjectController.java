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
}
