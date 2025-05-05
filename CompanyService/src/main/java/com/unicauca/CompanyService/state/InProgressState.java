package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.entity.ProjectStatus;

public class InProgressState implements ProjectState {

    @Override
    public void approve(Project project) {
        throw new IllegalStateException("Cannot approve project. Already past acceptance. Current state: IN_PROGRESS");
    }

    @Override
    public void reject(Project project) {
        // ¿Se puede cancelar/rechazar un proyecto en progreso?
        // Si es posible:
        // System.out.println("Project " + project.getId() + " cancelled/rejected while IN_PROGRESS.");
        // project.setStatus(ProjectStatus.REJECTED); // o un estado CANCELLED si existiera
        throw new IllegalStateException("Cannot reject project while IN_PROGRESS (unless cancellation logic defined).");
    }

    @Override
    public void assign(Project project) {
        throw new IllegalStateException("Project is already IN_PROGRESS.");
    }

    @Override
    public void complete(Project project) {
        System.out.println("Project " + project.getId() + " completed and moved to CLOSED.");
        project.setStatus(ProjectStatus.CLOSED);
    }

    @Override
    public void markAsReceived(Project project) {
        throw new IllegalStateException("Cannot mark as RECEIVED from IN_PROGRESS state.");
    }
    @Override
    public boolean isValidNextState(ProjectStatus nextStatus) {
        // Desde IN_PROGRESS, se puede pasar a CLOSED (vía complete) o quizás REJECTED (cancelar)
        return nextStatus == ProjectStatus.CLOSED || nextStatus == ProjectStatus.IN_PROGRESS;
        // || nextStatus == ProjectStatus.REJECTED; // Descomenta si permites cancelar desde aquí
    }
}
