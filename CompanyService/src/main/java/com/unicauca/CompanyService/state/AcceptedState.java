package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.entity.ProjectStatus;

public class AcceptedState implements ProjectState {

    @Override
    public void approve(Project project) {
        throw new IllegalStateException("Project is already ACCEPTED.");
    }

    @Override
    public void reject(Project project) {
        // Podría ser posible rechazar después de aceptar? Si no, lanza excepción.
        // Si es posible:
        // System.out.println("Project " + project.getId() + " rejected after acceptance.");
        // project.setStatus(ProjectStatus.REJECTED);
        throw new IllegalStateException("Cannot reject an already ACCEPTED project.");
    }

    @Override
    public void assign(Project project) {
        System.out.println("Project " + project.getId() + " assigned and moved to IN_PROGRESS.");
        project.setStatus(ProjectStatus.IN_PROGRESS);
    }

    @Override
    public void complete(Project project) {
        throw new IllegalStateException("Cannot complete project. Must be IN_PROGRESS first. Current state: ACCEPTED");
    }

    @Override
    public void markAsReceived(Project project) {
        throw new IllegalStateException("Cannot mark as RECEIVED from ACCEPTED state.");
    }

    @Override
    public boolean isValidNextState(ProjectStatus nextStatus) {
        // Desde ACCEPTED, se puede pasar a IN_PROGRESS (vía assign) o quizás REJECTED (si se permite cancelar)
        return nextStatus == ProjectStatus.IN_PROGRESS || nextStatus == ProjectStatus.ACCEPTED;
        // || nextStatus == ProjectStatus.REJECTED; // Descomenta si permites cancelar desde aquí
    }
}
