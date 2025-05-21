package com.unicauca.CoordinatorService.application.port.out;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByStatusNot(ProjectStatus status);
}
