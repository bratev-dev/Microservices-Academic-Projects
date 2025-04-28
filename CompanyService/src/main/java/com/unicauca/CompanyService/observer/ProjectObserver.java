package com.unicauca.CompanyService.observer;

import com.unicauca.CompanyService.entity.Project;

public class ProjectObserver implements IObserver {

    @Override
    public void update(Project project, String message) {
        System.out.println("Notificación del proyecto [" + project.getName() + "]: " + message);
    }
}
