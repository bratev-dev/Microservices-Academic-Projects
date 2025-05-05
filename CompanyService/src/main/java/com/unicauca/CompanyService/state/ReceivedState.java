package com.unicauca.CompanyService.state;
import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.entity.ProjectStatus;
public class ReceivedState implements ProjectState {

    @Override
    public void approve(Project project) {
        System.out.println("Project " + project.getId() + " approved.");
        project.setStatus(ProjectStatus.ACCEPTED); // Cambia el estado en el objeto Project
    }

    @Override
    public void reject(Project project) {
        System.out.println("Project " + project.getId() + " rejected.");
        project.setStatus(ProjectStatus.REJECTED);
    }

    @Override
    public void assign(Project project) {
        throw new IllegalStateException("Cannot assign project. Must be approved first. Current state: RECEIVED");
    }

    @Override
    public void complete(Project project) {
        throw new IllegalStateException("Cannot complete project. Not yet started. Current state: RECEIVED");
    }

    @Override
    public void markAsReceived(Project project) {
        // Ya está en este estado, no hacer nada o lanzar excepción
        System.out.println("Project " + project.getId() + " is already in RECEIVED state.");
        // throw new IllegalStateException("Project is already in RECEIVED state.");
    }

    @Override
    public boolean isValidNextState(ProjectStatus nextStatus) {
        // Desde RECEIVED, solo se puede pasar a ACCEPTED o REJECTED (a través de acciones)
        // Si permitimos setear directamente vía PUT:
        return nextStatus == ProjectStatus.ACCEPTED || nextStatus == ProjectStatus.REJECTED || nextStatus == ProjectStatus.RECEIVED;
        // O si quieres ser más estricto y forzar acciones:
        // return nextStatus == ProjectStatus.RECEIVED; // Solo permite quedarse en el mismo estado vía PUT
    }
}
