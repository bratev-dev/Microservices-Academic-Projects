package com.unicauca.CoordinatorService.infraestructure.infraestructure.persistence.repository;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;

public class ProjectMapper {

    public static JpaProjectEntity toJpaEntity(Project project) {
        if (project == null) {
            return null;
        }

        return JpaProjectEntity.builder()
                .id(project.getId())
                .name(project.getName())
                .summary(project.getSummary())
                .goals(project.getGoals())
                .description(project.getDescription())
                .maxtimeMonths(project.getMaxtimeMonths())
                .budget(project.getBudget())
                .date(project.getDate())
                .comments(project.getComments())
                .companyId(project.getCompanyId())
                .assignedTo(project.getAssignedTo())
                .status(project.getStatus() != null ? StatusMapper.toJpaStatus(project.getStatus()) : null) // Validación para null
                .build();
    }

    public static Project toDomainEntity(JpaProjectEntity jpaProject) {
        if (jpaProject == null) return null;

        return new Project(
                jpaProject.getId(),
                jpaProject.getName(),
                jpaProject.getSummary(),
                jpaProject.getGoals(),
                jpaProject.getDescription(),
                jpaProject.getMaxtimeMonths(),
                jpaProject.getBudget(),
                jpaProject.getDate(),
                jpaProject.getComments(),
                jpaProject.getCompanyId(),
                jpaProject.getAssignedTo(),
                StatusMapper.toDomainStatus(jpaProject.getStatus())
        );
    }


}