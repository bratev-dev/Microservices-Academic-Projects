package com.unicauca.CoordinatorService.adapter.out.persistence;

import com.unicauca.CoordinatorService.adapter.in.rest.ProjectServicePortImpl;
import com.unicauca.CoordinatorService.application.port.out.CompanyServiceClient;
import com.unicauca.CoordinatorService.domain.state.ProjectState;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;

import com.unicauca.CoordinatorService.infraestructure.persistence.repository.JpaProjectSpringDataRepository;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {


    private final JpaProjectSpringDataRepository projectRepository;

    private final CompanyServiceClient companyServiceClient;
    private final ProjectServicePortImpl projectServicePortImpl;
    public ProjectService(JpaProjectSpringDataRepository projectRepository, CompanyServiceClient companyServiceClient, ProjectServicePortImpl projectServicePortImpl) {
        this.projectRepository = projectRepository;
        this.companyServiceClient = companyServiceClient;
        this.projectServicePortImpl = projectServicePortImpl;
    }

    public List<ProjectDTO> getAllProjects() {
        return companyServiceClient.getAllProjects();
    }

    public JpaProjectEntity getProjectById(String id) {
        return companyServiceClient.getProjectById(Long.valueOf(id));
    }
    @Transactional
    public JpaProjectEntity evaluateProject(Long id, EvaluationRequest request) {
        // 1. Obtener proyecto del microservicio compañía
        JpaProjectEntity jpaProjectEntity = companyServiceClient.getProjectById(Long.valueOf(id));

        // 2. Actualizar campos editables
        if(request.getComments() != null) {
            jpaProjectEntity.setComments(request.getComments());
        }
        if(request.getAssignedTo() != null) {
            jpaProjectEntity.setAssignedTo(request.getAssignedTo());
        }

        // 3. Cambiar estado (esto actualiza solo el estado)
        JpaProjectEntity updatedEntity = projectServicePortImpl.changeStatus(id, request.getStatus());

        // 4. Combinar cambios (estado + otros campos)
        updatedEntity.setComments(jpaProjectEntity.getComments());
        updatedEntity.setAssignedTo(jpaProjectEntity.getAssignedTo());

        // 5. Guardar en el microservicio compañía
        return companyServiceClient.updateProject(id, updatedEntity);
    }

    public List<JpaProjectEntity> getPendingProjects() {
        return projectRepository.findByStatus(JpaProjectStatusEntity.IN_PROGRESS);
    }

    public List<JpaProjectEntity> getEvaluatedProjects() {
        return projectRepository.findByStatusNot(JpaProjectStatusEntity.IN_PROGRESS);
    }

}
