package com.unicauca.CoordinatorService.adapter.out.proxy;

import com.unicauca.CoordinatorService.application.port.out.CompanyRepositoryPort;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;

import java.util.List;

public class CompanyRepositoryProxy implements CompanyRepositoryPort {

    private final CompanyRepositoryPort target; // El real (FeignCompanyRepositoryClient)

    public CompanyRepositoryProxy(CompanyRepositoryPort target) {
        System.out.println("CompanyServiceProxy iniciado. Bean real: " + target.getClass());
        this.target = target;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        System.out.println("[Proxy] Llamando a getAllProjects");
        return target.getAllProjects();
    }

    @Override
    public JpaProjectEntity getProjectById(Long id) {
        System.out.println("[Proxy] Obteniendo proyecto con ID: " + id);
        return target.getProjectById(id);
    }

    @Override
    public JpaProjectEntity updateProject(Long id, JpaProjectEntity updatedJpaProjectEntity) {
        System.out.println("[Proxy] Actualizando proyecto ID: " + id);
        return target.updateProject(id, updatedJpaProjectEntity);
    }

    @Override
    public JpaProjectEntity changeProjectStatus(Long id, JpaProjectStatusEntity newStatus) {
        System.out.println("[Proxy] Cambio de estado solicitado para ID: " + id);
        return target.changeProjectStatus(id, newStatus);
    }

    @Override
    public List<JpaProjectEntity> getPendingProjects() {
        return target.getPendingProjects();
    }

    @Override
    public List<JpaProjectEntity> getEvaluatedProjects() {
        return target.getEvaluatedProjects();
    }
}
