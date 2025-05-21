package com.unicauca.CoordinatorService.Patterns.state;

import com.unicauca.CoordinatorService.entity.Project;
import com.unicauca.CoordinatorService.entity.ProjectStatus;
import com.unicauca.CoordinatorService.infra.exceptions.InvalidStateTransitionException;

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
