package com.unicauca.CompanyService.state;
import com.unicauca.CompanyService.entity.Project;
public class ApprovedState implements ProjectState {
    @Override
    public void approve(Project project) {
        System.out.println("El proyecto ya está aprobado.");
    }

    @Override
    public void reject(Project project) {
        System.out.println("No se puede rechazar un proyecto ya aprobado.");
    }

    @Override
    public void assign(Project project) {
        project.setState("Assigned");
        project.setProjectState(new AssignedState());
        System.out.println("Proyecto asignado.");
    }

    @Override
    public void complete(Project project) {
        System.out.println("El proyecto debe estar asignado para completarse.");
    }
    @Override
    public void handleState(Project project) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                "                                  STATE                                         \n" +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        project.setState("Approved");
    }
}

