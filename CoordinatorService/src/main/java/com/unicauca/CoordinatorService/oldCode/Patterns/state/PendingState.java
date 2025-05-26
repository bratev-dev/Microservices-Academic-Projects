package com.unicauca.CoordinatorService.oldCode.Patterns.state;

import com.unicauca.CoordinatorService.oldCode.entity.Project;
import com.unicauca.CoordinatorService.oldCode.entity.ProjectStatus;
import com.unicauca.CoordinatorService.oldCode.infra.exceptions.InvalidStateTransitionException;

public class PendingState implements ProjectState {
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
