package com.unicauca.CoordinatorService.infraestructure.persistence.repository;

import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;

public class StatusMapper {
    public static JpaProjectStatusEntity toJpaStatus(ProjectStatus domainStatus) {
        if (domainStatus == null) {
            return null;
        }

        switch (domainStatus) {
            case ACCEPTED:
                return JpaProjectStatusEntity.ACCEPTED;
            case IN_PROGRESS:
                return JpaProjectStatusEntity.IN_PROGRESS;
            case CLOSED:
                return JpaProjectStatusEntity.CLOSED;
            case RECEIVED:
                return JpaProjectStatusEntity.RECEIVED;
            case REJECTED:
                return JpaProjectStatusEntity.REJECTED;
            default:
                throw new IllegalArgumentException("Estado de dominio no soportado: " + domainStatus);
        }
    }

    public static ProjectStatus toDomainStatus(JpaProjectStatusEntity jpaStatus) {
        if (jpaStatus == null) {
            return null;
        }

        switch (jpaStatus) {
            case ACCEPTED:
                return ProjectStatus.ACCEPTED;
            case IN_PROGRESS:
                return ProjectStatus.IN_PROGRESS;
            case CLOSED:
                return ProjectStatus.CLOSED;
            case RECEIVED:
                return ProjectStatus.RECEIVED;
            case REJECTED:
                return ProjectStatus.REJECTED;
            default:
                throw new IllegalArgumentException("Estado JPA no soportado: " + jpaStatus);
        }
    }
}
