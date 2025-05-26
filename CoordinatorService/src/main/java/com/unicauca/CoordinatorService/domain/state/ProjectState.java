package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
/**
 *
 * @author jpala
 */
public interface ProjectState {
    void approve(Project project);
    void reject(Project project);
    void assign(Project project);
    void complete(Project project);
}
