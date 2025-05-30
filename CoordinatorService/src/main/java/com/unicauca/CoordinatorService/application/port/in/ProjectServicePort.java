package com.unicauca.CoordinatorService.application.port.in;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;

public interface ProjectServicePort {

    // Cambiar el estado de un proyecto
    JpaProjectEntity changeStatus(Long projectId, JpaProjectStatusEntity newState);
   //
}
