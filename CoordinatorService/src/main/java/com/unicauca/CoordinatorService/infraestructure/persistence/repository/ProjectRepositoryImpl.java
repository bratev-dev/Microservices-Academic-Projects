package com.unicauca.CoordinatorService.infraestructure.persistence.repository;


import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.repository.ProjectRepository;
import com.unicauca.CoordinatorService.domain.state.*;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
//import com.unicauca.CoordinatorService.infraestructure.persistence.repository.ProjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Primary
public class ProjectRepositoryImpl implements ProjectRepository {

    private final JpaProjectSpringDataRepository jpaRepository;

    public ProjectRepositoryImpl(JpaProjectSpringDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Project> findByStatus(String status) {
        return jpaRepository.findByStatus(status).stream()
                .map(ProjectMapper::toDomainEntity) // Convertir de JpaProjectEntity a Project (dominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findByStatusNot(String status) {
        return jpaRepository.findByStatusNot(status).stream()
                .map(ProjectMapper::toDomainEntity) // Convertir de JpaProjectEntity a Project (dominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Project> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ProjectMapper::toDomainEntity); // Convertir el proyecto específico a dominio
    }

    @Override
    public void changeStatus(Long projectId, ProjectState newState) {
        // Buscar el proyecto en la base de datos
        JpaProjectEntity jpaEntity = jpaRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("El proyecto con ID " + projectId + " no existe"));

        // Convertir la entidad encontrada al modelo de dominio
        Project domainProject = ProjectMapper.toDomainEntity(jpaEntity);

        // Cambiar el estado del proyecto usando el patrón State
        if (newState != null) {
            if (newState.getClass().getSimpleName().equals("ApprovedState")) {
                domainProject.approve();
            } else if (newState.getClass().getSimpleName().equals("AssignedState")) {
                domainProject.assign();
            } else if (newState.getClass().getSimpleName().equals("CompletedState")) {
                domainProject.complete();
            } else if (newState.getClass().getSimpleName().equals("RejectState")) {
                domainProject.reject();
            }
        }

        // Convertir nuevamente al modelo JPA y guardar los cambios
        JpaProjectEntity updatedJpaEntity = ProjectMapper.toJpaEntity(domainProject);
        jpaRepository.save(updatedJpaEntity);
    }

}




