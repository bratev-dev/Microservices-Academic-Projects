package com.unicauca.CoordinatorService.infraestructure.persistence.repository;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Hace el CRUD de JpaProjectEntity usando la API de Spring
 */
public interface JpaProjectSpringDataRepository extends JpaRepository<JpaProjectEntity, Long> {
    // Buscar proyectos por estado (status)
    List<JpaProjectEntity> findByStatus(String status);

    // Buscar proyectos que no tengan el estado (status) especificado
    List<JpaProjectEntity> findByStatusNot(String status);

}
