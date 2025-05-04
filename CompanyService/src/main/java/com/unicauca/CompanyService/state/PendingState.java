package com.unicauca.CompanyService.state;

import com.unicauca.CompanyService.entity.Project;

public class PendingState implements ProjectState {

    @Override
    public void approve(Project project) {
        project.setProjectState(new ApprovedState());
        System.out.println("Proyecto aprobado desde estado PENDING.");
    }

    @Override
    public void reject(Project project) {
        project.setProjectState(new RejectedState());
        System.out.println("Proyecto rechazado desde estado PENDING.");
    }

    @Override
    public void assign(Project project) {
        System.out.println("No se puede asignar un proyecto en estado PENDING.");
    }

    @Override
    public void complete(Project project) {
        System.out.println("No se puede completar un proyecto en estado PENDING.");
    }

    @Override
    public void handleState(Project project) {

    }
}



