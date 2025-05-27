package com.unicauca.CompanyService.controller;

import com.unicauca.CompanyService.dto.ProjectDTO;
import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.service.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // Permite que Postman o front acceda
public class ProjectController {

    @Autowired
    private ProjectService service;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.service = projectService;
    }

    // Traer cantidad de proyectos por estado
    @GetMapping("/count-by-state")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public ResponseEntity<Map<String, Long>> countProjectsByState() {
        Map<String, Long> counts = service.countProjectsByState();
        return ResponseEntity.ok(counts);
    }

    // Crear proyecto
    @PostMapping
    @PreAuthorize("hasRole('company')")
    public Project createProject(@RequestBody ProjectDTO projectDTO) {
        return service.createProject(projectDTO);
    }

    // Listar todos los proyectos
    @GetMapping
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public List<Project> getAllProjects() {
        return service.getAllProjects();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public Optional<Project> getProjectById(@PathVariable Long id) {  // Cambiar a Long
        return service.getProjectById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public Project updateProject(@PathVariable Long id, @RequestBody ProjectDTO updatedProjectDTO) {  // Cambiar a Long
        return service.updateProject(id, updatedProjectDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('company')")
    public boolean deleteProject(@PathVariable Long id) {  // Cambiar a Long
        return service.deleteProject(id);
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public String approveProject(@PathVariable Long id) {  // Cambiar a Long
        return service.approveProject(id);
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public String rejectProject(@PathVariable Long id) {  // Cambiar a Long
        return service.rejectProject(id);
    }

    @PostMapping("/{id}/assign")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public String assignProject(@PathVariable Long id) {  // Cambiar a Long
        return service.assignProject(id);
    }

    @PostMapping("/{id}/complete")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public String completeProject(@PathVariable Long id) {  // Cambiar a Long
        return service.completeProject(id);
    }
    // En ProjectController.java
    @PostMapping("/{id}/markAsReceived") // Cambia la ruta del endpoint
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public String markProjectAsReceived(@PathVariable Long id) {
        return service.markProjectAsReceived(id); // Llama al método renombrado
    }
}
