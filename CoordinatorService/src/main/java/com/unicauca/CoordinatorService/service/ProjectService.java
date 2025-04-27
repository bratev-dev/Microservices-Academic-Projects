package com.unicauca.CoordinatorService.service;

import com.unicauca.CoordinatorService.entity.Project;
import com.unicauca.CoordinatorService.entity.ProjectStatus;
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

    public Project acceptProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setStatus(ProjectStatus.ACCEPTED);
        return projectRepository.save(project);
    }

    public Project rejectProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setStatus(ProjectStatus.REJECTED);
        return projectRepository.save(project);
    }

    public Project ClosedProject(Long id, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        project.setStatus(ProjectStatus.CLOSED);
        return projectRepository.save(project);
    }

}
