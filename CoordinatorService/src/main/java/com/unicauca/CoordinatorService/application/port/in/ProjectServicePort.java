package com.unicauca.CoordinatorService.application.port.in;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;

import java.util.List;

public interface ProjectServicePort {

    List<ProjectDTO> getAllProjects();
    JpaProjectEntity getProjectById(String id);
    JpaProjectEntity evaluateProject(Long id, EvaluationRequest request);
    List<JpaProjectEntity> getPendingProjects();
    List<JpaProjectEntity> getEvaluatedProjects();
}
