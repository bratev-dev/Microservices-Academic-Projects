package com.unicauca.CompanyService.service;



import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.repository.ProjectRepository;
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

    public Optional<Project> updateProject(int id, Project updatedProject) {
        if (repository.existsById(id)) {
            updatedProject.setId(id);
            return Optional.of( repository.save(updatedProject));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteProject(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
