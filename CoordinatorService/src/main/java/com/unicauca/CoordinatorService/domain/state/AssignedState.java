package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.infra.exceptions.InvalidStateTransitionException;
/**
 *
 * @author jpala
 */
public class AssignedState implements ProjectState {
    @Override
    public void approve(Project project) {
        throw new InvalidStateTransitionException("No se puede .");
    }

    @Override
    public void reject(Project project) {
        throw new InvalidStateTransitionException("No se puede rechazar un proyecto asignado.");
    }

    @Override
    public void assign(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue asignado.");
    }

    @Override
    public void complete(Project project) {
        project.setStatus(ProjectStatus.COMPLETED);
    }

    public String toString() {
        return "AssignedState";
    }
}
