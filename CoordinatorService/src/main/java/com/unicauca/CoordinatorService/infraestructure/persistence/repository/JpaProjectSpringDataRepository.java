package com.unicauca.CoordinatorService.infraestructure.persistence.repository;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hace el CRUD de JpaProjectEntity usando la API de Spring
 */
//public interface ProjectRepository extends JpaRepository<JpaProjectEntity, Long>
@Repository
public interface JpaProjectSpringDataRepository extends JpaRepository<JpaProjectEntity, Long> {
    List<JpaProjectEntity> findByStatus(JpaProjectStatusEntity status);
    List<JpaProjectEntity> findByStatusNot(JpaProjectStatusEntity status);

}
