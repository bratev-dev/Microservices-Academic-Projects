package com.unicauca.CoordinatorService.Patterns.state;

import com.unicauca.CoordinatorService.entity.Project;

public interface ProjectState {
    void approve(Project project);

    void reject(Project project);

    void assign(Project project);

    void complete(Project project);
}
