package com.unicauca.CoordinatorService.infraestructure;


import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.ProjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaProjectEntityMapperTest {

    @Test
    void shouldMapProjectToJpaEntity() {
        // Arrange: Crea un objeto Project (del dominio)
        Project project = new Project(
                1L,
                "Project Name",
                "Project Summary",
                "Project Goals",
                "Project Description",
                12,
                50000.0,
                java.time.LocalDate.of(2023, 10, 20),
                "Test Comments",
                101L,
                102L,
                ProjectStatus.IN_PROGRESS
        );

        // Act: Mapear Project a JpaProjectEntity
        JpaProjectEntity jpaEntity = ProjectMapper.toJpaEntity(project);

        // Assert: Verificar que el contenido mapeado es correcto
        assertNotNull(jpaEntity);
        assertEquals(project.getId(), jpaEntity.getId());
        assertEquals(project.getName(), jpaEntity.getName());
        assertEquals(project.getSummary(), jpaEntity.getSummary());
        assertEquals(project.getGoals(), jpaEntity.getGoals());
        assertEquals(project.getDescription(), jpaEntity.getDescription());
        assertEquals(project.getMaxtimeMonths(), jpaEntity.getMaxtimeMonths());
        assertEquals(project.getBudget(), jpaEntity.getBudget());
        assertEquals(project.getDate(), jpaEntity.getDate());
        assertEquals(project.getComments(), jpaEntity.getComments());
        assertEquals(project.getCompanyId(), jpaEntity.getCompanyId());
        assertEquals(project.getAssignedTo(), jpaEntity.getAssignedTo());
        assertEquals(JpaProjectStatusEntity.IN_PROGRESS, jpaEntity.getStatus());
    }

    @Test
    void shouldMapJpaEntityToProject() {
        // Arrange: Crea un objeto JpaProjectEntity
        JpaProjectEntity jpaEntity = JpaProjectEntity.builder()
                .id(1L)
                .name("Project Name")
                .summary("Project Summary")
                .maxtimeMonths(12)
                .budget(50000.0)
                .date(java.time.LocalDate.of(2023, 10, 20))
                .comments("Test Comments")
                .companyId(101L)
                .assignedTo(102L)
                .status(JpaProjectStatusEntity.IN_PROGRESS)
                .build();

        // Act: Mapear JpaProjectEntity a Project
        Project project = ProjectMapper.toDomainEntity(jpaEntity);

        // Assert: Verificar que el contenido mapeado es correcto
        assertNotNull(project);
        assertEquals(jpaEntity.getId(), project.getId());
        assertEquals(jpaEntity.getName(), project.getName());
        assertEquals(jpaEntity.getSummary(), project.getSummary());
        assertEquals(jpaEntity.getGoals(), project.getGoals());
        assertEquals(jpaEntity.getDescription(), project.getDescription());
        assertEquals(jpaEntity.getMaxtimeMonths(), project.getMaxtimeMonths());
        assertEquals(jpaEntity.getBudget(), project.getBudget());
        assertEquals(jpaEntity.getDate(), project.getDate());
        assertEquals(jpaEntity.getComments(), project.getComments());
        assertEquals(jpaEntity.getCompanyId(), project.getCompanyId());
        assertEquals(jpaEntity.getAssignedTo(), project.getAssignedTo());
        assertEquals(ProjectStatus.IN_PROGRESS, project.getStatus());
    }

}

