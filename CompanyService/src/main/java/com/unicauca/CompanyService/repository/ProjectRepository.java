package com.unicauca.CompanyService.repository;


import com.unicauca.CompanyService.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
}
