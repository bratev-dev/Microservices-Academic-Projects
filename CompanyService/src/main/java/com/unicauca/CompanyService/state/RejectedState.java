package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.entity.ProjectStatus;

public class RejectedState implements ProjectState {
    // En un estado final como REJECTED, usualmente ninguna acción es válida.
    @Override
    public void approve(Project project) {
        // Podría permitirse re-evaluar? Si es así, cambiar a RECEIVED o ACCEPTED.
        // Si no:
        throw new IllegalStateException("Cannot approve a REJECTED project.");
    }

    @Override
    public void reject(Project project) {
        throw new IllegalStateException("Project is already REJECTED.");
    }

    @Override
    public void assign(Project project) {
        throw new IllegalStateException("Cannot assign a REJECTED project.");
    }

    @Override
    public void complete(Project project) {
        throw new IllegalStateException("Cannot complete a REJECTED project.");
    }

    @Override
    public void markAsReceived(Project project) {
        // Permitir volver a RECEIVED?
        // System.out.println("Project " + project.getId() + " marked as RECEIVED again for re-evaluation.");
        // project.setStatus(ProjectStatus.RECEIVED);
        throw new IllegalStateException("Cannot mark as RECEIVED from REJECTED state (unless re-evaluation allowed).");
    }

    @Override
    public boolean isValidNextState(ProjectStatus nextStatus) {
        // Estado final, no se debería poder cambiar (excepto quizás a RECEIVED si hay re-evaluación)
        return nextStatus == ProjectStatus.REJECTED;
        // || nextStatus == ProjectStatus.RECEIVED; // Descomenta si permites re-evaluar
    }
}