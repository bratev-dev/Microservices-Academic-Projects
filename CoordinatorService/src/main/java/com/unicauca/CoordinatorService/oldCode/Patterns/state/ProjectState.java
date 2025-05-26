package com.unicauca.CoordinatorService.oldCode.Patterns.state;

import com.unicauca.CoordinatorService.oldCode.entity.Project;

public interface ProjectState {
    void approve(Project project);

    void reject(Project project);

    void assign(Project project);

    void complete(Project project);
}
