package com.unicauca.CoordinatorService.controller;

import com.unicauca.CoordinatorService.entity.Project;
import com.unicauca.CoordinatorService.infra.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {
    @Autowired
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/inprogress")
    public List<Project> getInProgressProjects() {
        return projectService.getInProgessProjects();
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
}
