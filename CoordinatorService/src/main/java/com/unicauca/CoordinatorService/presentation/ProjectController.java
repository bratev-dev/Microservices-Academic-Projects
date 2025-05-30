package com.unicauca.CoordinatorService.presentation;

import com.unicauca.CoordinatorService.adapter.in.rest.ProjectServicePortImpl;
import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import com.unicauca.CoordinatorService.adapter.out.persistence.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectServicePortImpl projectServicePortImpl;
    public ProjectController(ProjectService projectService, ProjectServicePortImpl projectServicePortImpl) {
        this.projectService = projectService;
        this.projectServicePortImpl = projectServicePortImpl;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }
    
    @GetMapping("/inprogress")
    public List<JpaProjectEntity> getInProgressProjects() {
        return projectService.getPendingProjects();
    }

    @GetMapping("/evaluated")
    public List<JpaProjectEntity> getEvaluatedProjects() {
        return projectService.getEvaluatedProjects();
    }

    @PutMapping("/{id}/evaluate")
    public ResponseEntity<JpaProjectEntity> evaluateProject(@PathVariable Long id, @RequestBody EvaluationRequest request) {
        JpaProjectEntity updated = projectService.evaluateProject(id, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JpaProjectEntity> getProjectById(@PathVariable Long id) {
        JpaProjectEntity projectDTO = projectService.getProjectById(String.valueOf(id));
        if (projectDTO != null) {
            return ResponseEntity.ok(projectDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
