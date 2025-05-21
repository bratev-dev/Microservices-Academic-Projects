package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.entity.ProjectStatus;
public interface ProjectState {
    // Intenta aprobar el proyecto. Lanza excepción si no es válido desde este estado.
    void approve(Project project);

    // Intenta rechazar el proyecto. Lanza excepción si no es válido desde este estado.
    void reject(Project project);

    // Intenta asignar/iniciar el proyecto. Lanza excepción si no es válido desde este estado.
    void assign(Project project);

    // Intenta completar/cerrar el proyecto. Lanza excepción si no es válido desde este estado.
    void complete(Project project);

    // Intenta marcar como recibido (puede ser útil si hay re-evaluaciones)
    // Si RECEIVED es solo inicial, este método podría lanzar excepción en todos los estados.
    void markAsReceived(Project project);
    boolean isValidNextState(ProjectStatus nextStatus);

}
