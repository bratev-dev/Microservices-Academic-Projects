package com.unicauca.CompanyService.observer;

import com.unicauca.CompanyService.entity.Project;

public interface IObserver {
    void update(Project project, String message);
}

