package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.infraestructure.exceptions.InvalidStateTransitionException;

public class Accepted implements ProjectState {
    @Override
    public void received(Project project) {
        throw new InvalidStateTransitionException("No se puede retroceder de ACCEPTED a RECEIVED.");
    }

    @Override
    public void accepted(Project project) {
        throw new InvalidStateTransitionException("El proyecto ya está en estado ACCEPTED.");
    }

    @Override
    public void rejected(Project project) {
        throw new InvalidStateTransitionException("No se puede pasar de ACCEPTED a REJECTED.");
    }

    @Override
    public void inProgress(Project project) {
        project.setStatus(ProjectStatus.IN_PROGRESS);
    }

    @Override
    public void closed(Project project) {
        project.setStatus(ProjectStatus.CLOSED);
    }

}
