package com.mycompany.gestionproyectosacademicos.services;

import com.mycompany.gestionproyectosacademicos.access.IProjectRepository;
import com.mycompany.gestionproyectosacademicos.entities.Project;
import com.mycompany.gestionproyectosacademicos.observer.IObserver;
import com.mycompany.gestionproyectosacademicos.observer.IObservable;
import com.mycompany.gestionproyectosacademicos.observer.Subject;
import java.util.ArrayList;
import java.util.List;

public class ProjectService implements Subject {

    private IProjectRepository repo;
    private List<Project> projects;
    private final List<IObserver> observers = new ArrayList<>();

    public ProjectService(IProjectRepository repository) {
        this.repo = repository;
    }

    public void addProject(Project project) {
        repo.saveProject(project); // Agrega al repositorio
        notifyObservers(); // Notifica a todos los observers que la lista cambió
    }

    public List<Project> getProjects() {
        List<Project> projects = repo.getAllProjects();
        return projects;
    }

    // Método para obtener proyectos por período académico
    public List<Project> getProjectsByAcademicPeriod(String academicPeriod) {
        return repo.getProjectsByAcademicPeriod(academicPeriod);
    }

    public void deleteProject(int projectId) {
        repo.deleteProject(projectId);
        notifyObservers();
    }

    public boolean evaluateProject(Long projectId, String newStatus) {
        boolean success = repo.evaluateProject(projectId, newStatus);
        if (success) {
            notifyObservers();
        }
        return success;
    }
    
    // Métodos de Observer
    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(projects); // Notificar a los observadores con la lista de proyectos
        }
    }

    public int getNextProjectId() {
        return repo.getNextProjectId();
    }

}
