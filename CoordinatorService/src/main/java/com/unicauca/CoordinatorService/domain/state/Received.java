package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.infraestructure.exceptions.InvalidStateTransitionException;

public class Received implements ProjectState {
    @Override
    public void received(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya está en estado RECEIVED.");
    }

    @Override
    public void accepted(Project project) {project.setStatus(ProjectStatus.ACCEPTED);}

    @Override
    public void rejected(Project project) {
        project.setStatus(ProjectStatus.REJECTED);
    }

    @Override
    public void inProgress(Project project) {
        throw new InvalidStateTransitionException("No se puede pasar directamente de RECEIVED a IN_PROGRESS.");
    }

    @Override
    public void closed(Project project) {
        throw new InvalidStateTransitionException("No se puede pasar directamente de RECEIVED a CLOSED.");
    }
}