package com.unicauca.CoordinatorService.oldCode.controller;

import com.unicauca.CoordinatorService.oldCode.entity.Project;
import com.unicauca.CoordinatorService.oldCode.infra.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.oldCode.infra.dto.ProjectDTO;
import com.unicauca.CoordinatorService.oldCode.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }
    
    @GetMapping("/inprogress")
    public List<Project> getInProgressProjects() {
        return projectService.getPendingProjects();
    }

    @GetMapping("/evaluated")
    public List<Project> getEvaluatedProjects() {
        return projectService.getEvaluatedProjects();
    }

    @PutMapping("/{id}/evaluate")
    public ResponseEntity<Project> evaluateProject(@PathVariable Long id, @RequestBody EvaluationRequest request) {
        Project updated = projectService.evaluateProject(id, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        ProjectDTO projectDTO = projectService.getProjectById(String.valueOf(id));
        if (projectDTO != null) {
            return ResponseEntity.ok(projectDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
