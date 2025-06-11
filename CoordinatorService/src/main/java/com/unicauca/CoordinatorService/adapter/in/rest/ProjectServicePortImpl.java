package com.unicauca.CoordinatorService.adapter.in.rest;


import com.unicauca.CoordinatorService.adapter.out.mapper.EvaluationRequestAdapter;
import com.unicauca.CoordinatorService.application.port.out.CompanyRepositoryPort;
import com.unicauca.CoordinatorService.application.port.out.CompanyServiceClient;
import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.application.port.in.ProjectServicePort;
import com.unicauca.CoordinatorService.domain.state.*;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.JpaProjectSpringDataRepository;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.ProjectMapper;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class ProjectServicePortImpl implements ProjectServicePort {

    private final CompanyRepositoryPort repositoryPort;
    private final EvaluationRequestAdapter evaluationRequestAdapter;
    public ProjectServicePortImpl(@Qualifier("companyRepositoryPort") CompanyRepositoryPort repositoryPort, EvaluationRequestAdapter evaluationRequestAdapter) {
        this.repositoryPort = repositoryPort;
        this.evaluationRequestAdapter = evaluationRequestAdapter;
    }
    @Override
    public List<ProjectDTO> getAllProjects() {
        return repositoryPort.getAllProjects();
    }
    @Override
    public JpaProjectEntity getProjectById(String id) {
        return repositoryPort.getProjectById(Long.valueOf(id));
    }
    @Override
    public JpaProjectEntity evaluateProject(Long id, EvaluationRequest request) {

        // 1. Cambiar estado (esto actualiza solo el estado)
        JpaProjectEntity updatedEntity = repositoryPort.changeProjectStatus(id, request.getStatus());
        // 2. Aplicamos el patron para aplicar cambios de los campos
        evaluationRequestAdapter.applyChanges(updatedEntity, request);


        // 5. Guardar en el microservicio compañía
        return repositoryPort.updateProject(id, updatedEntity);
    }
    @Override
    public List<JpaProjectEntity> getPendingProjects() {
        return repositoryPort.getPendingProjects();
    }
    @Override
    public List<JpaProjectEntity> getEvaluatedProjects() {
        return repositoryPort.getEvaluatedProjects();
    }
}




