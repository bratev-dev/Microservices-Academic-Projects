package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;

public class RejectedState implements ProjectState {

    @Override
    public void approve(Project project) {
        System.out.println("No se puede aprobar un proyecto rechazado.");
    }

    @Override
    public void reject(Project project) {
        System.out.println("El proyecto ya está rechazado.");
    }

    @Override
    public void assign(Project project) {
        System.out.println("No se puede asignar un proyecto rechazado.");
    }

    @Override
    public void complete(Project project) {
        System.out.println("No se puede completar un proyecto rechazado.");
    }

    @Override
    public void handleState(Project project) {

    }
}
