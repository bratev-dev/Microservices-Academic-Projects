package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.infra.exceptions.InvalidStateTransitionException;

/**
 *
 * @author jpala
 */
public class ApprovedState implements ProjectState {

    @Override
    public void approve(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue aprobado.");
    }

    @Override
    public void reject(Project project) {
        throw new InvalidStateTransitionException("No se puede rechazar un proyecto aceptado.");
    }

    @Override
    public void assign(Project project) {
        project.setStatus(ProjectStatus.ASSIGNED);
    }

    @Override
    public void complete(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue completado.");
    }

    public String toString(){
        return "ApprovedState";
    }
    
}
