package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.infra.exceptions.InvalidStateTransitionException;
/**
 *
 * @author jpala
 */
public class CompletedState implements ProjectState{
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
