package com.mycompany.gestionproyectosacademicos.services;

import com.mycompany.gestionproyectosacademicos.access.IProjectRepository;
import com.mycompany.gestionproyectosacademicos.entities.Project;
import com.mycompany.gestionproyectosacademicos.observer.IObserver;
import com.mycompany.gestionproyectosacademicos.observer.Subject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectService implements Subject {

    private IProjectRepository repo;
    private List<Project> projects;
    private final List<IObserver> observers = new ArrayList<>();

    public ProjectService(IProjectRepository repository) {
        this.repo = repository;
        this.projects = repo.getAllProjects();
    }

    /**
    * @brief Obtiene el conteo de proyectos agrupados por su estado.
    * 
    * @return Mapa donde la clave es el nombre del estado y el valor es la cantidad de proyectos en ese estado.
    */
    public Map<String, Long> countProjectsByState(List<Project> projects) {
        return projects.stream()
                .collect(Collectors.groupingBy(Project::getStatus, Collectors.counting()));
    }
    
    public void addProject(Project project) {
        repo.saveProject(project); // Agrega al repositorio
        notifyObservers(); // Notifica a todos los observers que la lista cambió
    }

    public List<Project> getProjects() {
        this.projects = repo.getAllProjects();
        return projects;
    }

    // Método para obtener proyectos por período académico
    public List<Project> getProjectsByAcademicPeriod(String academicPeriod) {
        return repo.getProjectsByAcademicPeriod(academicPeriod);
    }

  /*  public void deleteProject(int projectId) {
        repo.deleteProject(projectId);
        notifyObservers();
    }
*/
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
        this.projects = repo.getAllProjects();
        for (IObserver observer : observers) {
            observer.update(projects); // Notificar a los observadores con la lista de proyectos
        }
    }
    
    public Project getProjectById(int id) throws SQLException {
        return repo.getProjectById(id);
    }
    
    public void clearProjects() {
        this.projects.clear();
    }
/*
    public int getNextProjectId() {
        return repo.getNextProjectId();
    }*/

}
