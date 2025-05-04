package com.unicauca.CompanyService.state;
import com.unicauca.CompanyService.entity.Project;
public interface ProjectState {
    void approve(Project project);
    void reject(Project project);
    void assign(Project project);
    void complete(Project project);

    void handleState(Project project);
}
