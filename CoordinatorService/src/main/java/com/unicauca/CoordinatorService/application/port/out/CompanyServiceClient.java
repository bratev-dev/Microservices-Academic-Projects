package com.unicauca.CoordinatorService.application.port.out;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "company-service", url = "http://localhost:8083/company")
public interface CompanyServiceClient {

    @GetMapping("/api/projects")
    List<ProjectDTO> getAllProjects();

    @GetMapping("/api/projects/{id}")
    JpaProjectEntity getProjectById(@PathVariable("id") Long id);

    @PutMapping("/api/projects/{id}")
    JpaProjectEntity updateProject(@PathVariable("id") Long id, @RequestBody JpaProjectEntity updatedJpaProjectEntity);
}
