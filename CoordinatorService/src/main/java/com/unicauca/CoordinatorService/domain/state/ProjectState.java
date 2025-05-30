package com.unicauca.CoordinatorService.domain.state;

import com.unicauca.CoordinatorService.domain.model.Project;
/**
 *
 * @author jpala
 */
public interface ProjectState {
    void received(Project project);
    void accepted(Project project);
    void rejected(Project project);
    void inProgress(Project project);
    void closed(Project project);
}
