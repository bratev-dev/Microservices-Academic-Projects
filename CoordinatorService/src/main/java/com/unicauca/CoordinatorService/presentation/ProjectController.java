package com.unicauca.CoordinatorService.presentation;

import com.unicauca.CoordinatorService.adapter.in.rest.ProjectServicePortImpl;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {
    private final ProjectServicePortImpl projectService;
    public ProjectController(ProjectServicePortImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @PreAuthorize("hasRole('coordinator')")
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/inprogress")
    @PreAuthorize("hasRole('coordinator')")
    public List<JpaProjectEntity> getInProgressProjects() {
        return projectService.getPendingProjects();
    }

    @GetMapping("/evaluated")
    @PreAuthorize("hasRole('coordinator')")
    public List<JpaProjectEntity> getEvaluatedProjects() {
        return projectService.getEvaluatedProjects();
    }

    @PutMapping("/{id}/evaluate")
    @PreAuthorize("hasRole('coordinator')")
    public ResponseEntity<JpaProjectEntity> evaluateProject(@PathVariable Long id, @RequestBody EvaluationRequest request) {
        JpaProjectEntity updated = projectService.evaluateProject(id, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('coordinator')")
    public ResponseEntity<JpaProjectEntity> getProjectById(@PathVariable Long id) {
        JpaProjectEntity projectDTO = projectService.getProjectById(String.valueOf(id));
        if (projectDTO != null) {
            return ResponseEntity.ok(projectDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
