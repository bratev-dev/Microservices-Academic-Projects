package com.unicauca.CoordinatorService.domain.repository;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.state.ProjectState;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<Project> findByStatus(String status); // Buscar por estado
    List<Project> findByStatusNot(String status); // Buscar excluyendo un estado
    Optional<Project> findById(Long id); // Buscar por ID

    // Cambiar el estado de un proyecto
    void changeStatus(Long projectId, ProjectState newState);

}
