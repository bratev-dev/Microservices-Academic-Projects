package com.unicauca.CoordinatorService.service;

import com.unicauca.CoordinatorService.entity.Project;
import com.unicauca.CoordinatorService.entity.ProjectStatus;
import com.unicauca.CoordinatorService.infra.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(String id) {
        return projectRepository.findById(Long.valueOf(id));
    }

    public Project evaluateProject(Long id, EvaluationRequest req) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setStatus(req.getStatus());
        System.out.println("decisión tomada: " + req.getStatus());
        project.setComments(req.getComments());
        project.setAssignedTo(req.assignedTo);
        return projectRepository.save(project);
    }

    public List<Project> getPendingProjects() {
        return projectRepository.findByStatus(ProjectStatus.PENDING);
    }

    public List<Project> getEvaluatedProjects() {
        return projectRepository.findByStatusNot(ProjectStatus.PENDING);
    }

}
