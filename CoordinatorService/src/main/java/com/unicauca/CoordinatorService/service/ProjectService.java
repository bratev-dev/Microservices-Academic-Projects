package com.unicauca.CoordinatorService.service;

import com.unicauca.CoordinatorService.entity.Project;
import com.unicauca.CoordinatorService.entity.ProjectStatus;
import com.unicauca.CoordinatorService.infra.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.infra.dto.ProjectDTO;
import com.unicauca.CoordinatorService.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final CompanyServiceClient companyServiceClient;

    public ProjectService(ProjectRepository projectRepository, CompanyServiceClient companyServiceClient) {
        this.projectRepository = projectRepository;
        this.companyServiceClient = companyServiceClient;
    }

    public List<ProjectDTO> getAllProjects() {
        return companyServiceClient.getAllProjects();
    }

    public ProjectDTO getProjectById(String id) {
        return companyServiceClient.getProjectById(Long.valueOf(id));
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
