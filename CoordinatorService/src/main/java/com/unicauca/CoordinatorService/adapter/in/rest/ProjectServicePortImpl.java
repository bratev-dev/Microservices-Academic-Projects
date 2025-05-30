package com.unicauca.CoordinatorService.adapter.in.rest;


import com.unicauca.CoordinatorService.application.port.out.CompanyServiceClient;
import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.application.port.in.ProjectServicePort;
import com.unicauca.CoordinatorService.domain.state.*;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.JpaProjectSpringDataRepository;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.ProjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class ProjectServicePortImpl implements ProjectServicePort {

    private final CompanyServiceClient companyServiceClient;

    public ProjectServicePortImpl(CompanyServiceClient companyServiceClient) {
        this.companyServiceClient = companyServiceClient;
    }

    @Override
    public JpaProjectEntity changeStatus(Long projectId, JpaProjectStatusEntity newState) {
        // 1. Obtener entidad actual del servicio compañía
        JpaProjectEntity jpaEntity = companyServiceClient.getProjectById(projectId);

        // 2. Convertir a dominio
        Project domainProject = ProjectMapper.toDomainEntity(jpaEntity);
        if (domainProject == null) {
            throw new IllegalStateException("Failed to convert JPA entity to domain model");
        }

        // 3. Cambiar estado
        switch (newState) {
            case ACCEPTED -> domainProject.accept();
            case IN_PROGRESS -> domainProject.startProgress();
            case REJECTED -> domainProject.reject();
            case RECEIVED -> domainProject.markAsReceived();
            case CLOSED -> domainProject.close();
            default -> throw new IllegalArgumentException("Invalid state: " + newState);
        }

        // 4. Convertir y retornar
        return ProjectMapper.toJpaEntity(domainProject);
    }
}




