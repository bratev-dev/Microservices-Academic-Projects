package com.unicauca.CoordinatorService.repository;

import com.unicauca.CoordinatorService.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
