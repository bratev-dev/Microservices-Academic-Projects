package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.infraestructure.exceptions.InvalidStateTransitionException;

public class Closed implements ProjectState {
    @Override
    public void received(Project project) {
        throw new InvalidStateTransitionException("No se puede cambiar el estado de un proyecto CLOSED.");
    }

    @Override
    public void accepted(Project project) {
        throw new InvalidStateTransitionException("No se puede cambiar el estado de un proyecto CLOSED.");
    }

    @Override
    public void rejected(Project project) {
        throw new InvalidStateTransitionException("No se puede cambiar el estado de un proyecto CLOSED.");
    }

    @Override
    public void inProgress(Project project) {
        throw new InvalidStateTransitionException("No se puede cambiar el estado de un proyecto CLOSED.");
    }

    @Override
    public void closed(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya está en estado CLOSED.");
    }
}
