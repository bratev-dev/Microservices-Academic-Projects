package com.unicauca.CompanyService.service;

import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.repository.ProjectRepository;
import com.unicauca.CompanyService.state.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Transactional
    public Project createProject(Project project) {
        return repository.save(project);
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Optional<Project> getProjectById(int id) {
        return repository.findById(id);
    }
    @Transactional
    public Optional<Project> updateProject(int id, Project updatedProject) {
        Optional<Project> optional = repository.findById(id);

        if (optional.isPresent()) {
            Project existing = optional.get();

            // Actualizar campos normales
            existing.setName(updatedProject.getName());
            existing.setSummary(updatedProject.getSummary());
            existing.setGoals(updatedProject.getGoals());
            existing.setDescription(updatedProject.getDescription());
            existing.setMaxTimeInMonths(updatedProject.getMaxTimeInMonths());
            existing.setBudget(updatedProject.getBudget());
            existing.setDate(updatedProject.getDate());
            existing.setComments(updatedProject.getComments());
            existing.setCalificacion(updatedProject.getCalificacion());
            existing.setRequest(updatedProject.getRequest());
            existing.setCompany(updatedProject.getCompany());

            return Optional.of(repository.save(existing));
        }

        return Optional.empty();
    }

    public boolean deleteProject(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public String approveProject(int id) {
        System.out.println("Service: Approving project with ID: " + id);
        return changeState(id, new ApprovedState());
    }

    @Transactional
    public String rejectProject(int id) {
        System.out.println("Service: Rejecting project with ID: " + id);
        return changeState(id, new RejectedState());
    }

    @Transactional
    public String assignProject(int id) {
        System.out.println("Service: Assigning project with ID: " + id);
        return changeState(id, new AssignedState());
    }

    @Transactional
    public String completeProject(int id) {
        System.out.println("Service: Completing project with ID: " + id);
        return changeState(id, new CompletedState());
    }

    @Transactional
    public String pendingProject(int id) {
        System.out.println("Service: Setting project to Pending with ID: " + id);
        return changeState(id, new PendingState());
    }

    private String changeState(int id, ProjectState newState) {
        Optional<Project> optional = repository.findById(id);
        if (optional.isPresent()) {
            Project project = optional.get();

            System.out.println("Service: Current state before change: " + project.getState());

            project.setProjectState(newState); // Set the new state
            newState.handleState(project);     // Execute any state-specific logic

            // Important: Log the state *after* handleState()
            System.out.println("Service: State after handleState: " + project.getState());

            Project savedProject = repository.save(project); // Save the updated project
            System.out.println("Service: Project saved with state: " + savedProject.getState());

            return "✅ Estado actual: " + savedProject.getState();
        }
        System.out.println("Service: Project not found for state change with ID: " + id);
        return "❌ Proyecto no encontrado";
    }
}