package com.unicauca.CoordinatorService.application.port.out;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import java.util.List;

public interface CompanyRepositoryPort {
    List<ProjectDTO> getAllProjects();
    JpaProjectEntity getProjectById(Long id);
    JpaProjectEntity updateProject(Long id,JpaProjectEntity updatedJpaProjectEntity);
    JpaProjectEntity changeProjectStatus(Long id, JpaProjectStatusEntity newStatus);
    List<JpaProjectEntity> getPendingProjects();
    List<JpaProjectEntity> getEvaluatedProjects();
}
