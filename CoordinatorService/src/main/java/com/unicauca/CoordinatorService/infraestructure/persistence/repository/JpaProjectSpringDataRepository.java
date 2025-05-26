package com.unicauca.CoordinatorService.infraestructure.persistence.repository;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Hace el CRUD de JpaProjectEntity usando la API de Spring
 */
public interface JpaProjectSpringDataRepository extends JpaRepository<JpaProjectEntity, Long> {

}
