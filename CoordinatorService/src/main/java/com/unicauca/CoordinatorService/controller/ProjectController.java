package com.unicauca.CoordinatorService.controller;

import com.unicauca.CoordinatorService.entity.Project;
import com.unicauca.CoordinatorService.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PutMapping("/{id}/approve")
    public Project approveProject(@PathVariable Long id) {
        return projectService.rejectProject(id);
    }

    @PutMapping("/{id}/accept")
    public Project acceptProject(@PathVariable Long id) {
        return projectService.acceptProject(id);
    }
}
