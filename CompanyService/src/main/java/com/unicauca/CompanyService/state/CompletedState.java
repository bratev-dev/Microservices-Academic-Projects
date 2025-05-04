package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;

public class CompletedState implements ProjectState {

    @Override
    public void approve(Project project) {
        System.out.println("Proyecto ya completado, no se puede aprobar.");
    }

    @Override
    public void reject(Project project) {
        System.out.println("Proyecto ya completado, no se puede rechazar.");
    }

    @Override
    public void assign(Project project) {
        System.out.println("Proyecto ya completado, no se puede asignar.");
    }

    @Override
    public void complete(Project project) {
        System.out.println("El proyecto ya está completado.");
    }

    @Override
    public void handleState(Project project) {

    }
}
