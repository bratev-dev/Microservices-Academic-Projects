package com.unicauca.CoordinatorService.adapter.out.persistence;

import com.unicauca.CoordinatorService.application.port.out.CompanyServiceClient;
import com.unicauca.CoordinatorService.application.port.out.CompanyRepositoryPort;
import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.JpaProjectSpringDataRepository;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.ProjectMapper;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignCompanyRepositoryClient implements CompanyRepositoryPort {
    private final JpaProjectSpringDataRepository projectRepository;
    private final CompanyServiceClient companyServiceClient;

    public FeignCompanyRepositoryClient(CompanyServiceClient companyServiceClient, JpaProjectSpringDataRepository projectRepository) {
        this.companyServiceClient = companyServiceClient;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return companyServiceClient.getAllProjects();
    }

    @Override
    public JpaProjectEntity getProjectById(Long id) {
        return companyServiceClient.getProjectById(id);
    }

    @Override
    public JpaProjectEntity updateProject(Long id, JpaProjectEntity updatedJpaProjectEntity) {
        return companyServiceClient.updateProject(id, updatedJpaProjectEntity);
    }
    @Override
    public JpaProjectEntity changeProjectStatus(Long id, JpaProjectStatusEntity newStatus) {
        JpaProjectEntity jpa = companyServiceClient.getProjectById(id);
        Project domainProject = ProjectMapper.toDomainEntity(jpa);

        switch (newStatus) {
            case ACCEPTED -> domainProject.accept();
            case IN_PROGRESS -> domainProject.startProgress();
            case REJECTED -> domainProject.reject();
            case RECEIVED -> domainProject.markAsReceived();
            case CLOSED -> domainProject.close();
        }

        JpaProjectEntity updated = ProjectMapper.toJpaEntity(domainProject);
        return companyServiceClient.updateProject(id, updated);
    }

    @Override
    public List<JpaProjectEntity> getPendingProjects() {
        return projectRepository.findByStatus(JpaProjectStatusEntity.IN_PROGRESS);
    }

    @Override
    public List<JpaProjectEntity> getEvaluatedProjects() {
        return projectRepository.findByStatusNot(JpaProjectStatusEntity.IN_PROGRESS);
    }
}
