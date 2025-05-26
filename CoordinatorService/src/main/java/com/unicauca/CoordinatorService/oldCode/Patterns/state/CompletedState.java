package com.unicauca.CoordinatorService.oldCode.Patterns.state;

import com.unicauca.CoordinatorService.oldCode.entity.Project;
import com.unicauca.CoordinatorService.oldCode.infra.exceptions.InvalidStateTransitionException;

public class CompletedState implements ProjectState {
    @Override
    public void approve(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue completado.");
    }

    @Override
    public void reject(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue completado.");
    }

    @Override
    public void assign(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue completado.");
    }

    @Override
    public void complete(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue completado.");
    }
}
