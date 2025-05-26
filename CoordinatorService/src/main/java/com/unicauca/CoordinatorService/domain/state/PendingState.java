package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.oldCode.infra.exceptions.InvalidStateTransitionException;
/**
 *
 * @author jpala
 */
public class PendingState implements ProjectState{
    @Override
    public void approve(Project project) {
        project.setStatus(ProjectStatus.APPROVED);
    }

    @Override
    public void reject(Project project) {
        project.setStatus(ProjectStatus.REJECTED);
    }

    @Override
    public void assign(Project project) {
        project.setStatus(ProjectStatus.ASSIGNED);
    }

    @Override
    public void complete(Project project) {
        throw new InvalidStateTransitionException("El proyecto aún no fue asignado.");
    }
}
