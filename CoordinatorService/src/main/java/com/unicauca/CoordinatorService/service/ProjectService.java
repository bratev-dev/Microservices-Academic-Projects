package com.unicauca.CoordinatorService.service;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import com.unicauca.CoordinatorService.application.port.out.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        //project.setAssignedTo(req.assignedTo);
        project.setAssignedTo(req.getAssignedTo());
        return projectRepository.save(project);
    }

    public List<Project> getPendingProjects() {
        return projectRepository.findByStatus(ProjectStatus.PENDING);
    }

    public List<Project> getEvaluatedProjects() {
        return projectRepository.findByStatusNot(ProjectStatus.PENDING);
    }

}
