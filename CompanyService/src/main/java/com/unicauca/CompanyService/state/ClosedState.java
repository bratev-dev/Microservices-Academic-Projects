package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.entity.ProjectStatus;

public class ClosedState implements ProjectState {
    // Estado final, usualmente ninguna acción cambia el estado.
    @Override
    public void approve(Project project) {
        throw new IllegalStateException("Cannot approve a CLOSED project.");
    }

    @Override
    public void reject(Project project) {
        throw new IllegalStateException("Cannot reject a CLOSED project.");
    }

    @Override
    public void assign(Project project) {
        throw new IllegalStateException("Cannot assign a CLOSED project.");
    }

    @Override
    public void complete(Project project) {
        throw new IllegalStateException("Project is already CLOSED.");
    }

    @Override
    public void markAsReceived(Project project) {
        throw new IllegalStateException("Cannot mark as RECEIVED from CLOSED state.");
    }
    @Override
    public boolean isValidNextState(ProjectStatus nextStatus) {
        // Estado final, no se puede cambiar.
        return nextStatus == ProjectStatus.CLOSED;
    }
}