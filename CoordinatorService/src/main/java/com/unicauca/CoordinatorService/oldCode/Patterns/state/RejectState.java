package com.unicauca.CoordinatorService.oldCode.Patterns.state;

import com.unicauca.CoordinatorService.oldCode.entity.Project;
import com.unicauca.CoordinatorService.oldCode.infra.exceptions.InvalidStateTransitionException;

public class RejectState implements ProjectState {
    @Override
    public void approve(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue rechazado.");
    }

    @Override
    public void reject(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue rechazado.");
    }

    @Override
    public void assign(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue rechazado.");
    }

    @Override
    public void complete(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya fue rechazado.");
    }
}
