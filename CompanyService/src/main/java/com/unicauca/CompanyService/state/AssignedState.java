package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;

public class AssignedState implements ProjectState {

    @Override
    public void approve(Project project) {
        System.out.println("El proyecto ya está asignado.");
    }

    @Override
    public void reject(Project project) {
        System.out.println("No se puede rechazar un proyecto asignado.");
    }

    @Override
    public void assign(Project project) {
        System.out.println("El proyecto ya está asignado.");
    }

    @Override
    public void complete(Project project) {
        project.setProjectState(new CompletedState());
        System.out.println("Proyecto completado desde estado ASSIGNED.");
    }

    @Override
    public void handleState(Project project) {

    }
}
