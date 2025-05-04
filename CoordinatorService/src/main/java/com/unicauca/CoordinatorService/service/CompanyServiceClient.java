package com.unicauca.CoordinatorService.service;

import com.unicauca.CoordinatorService.infra.dto.ProjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "company-service", url = "http://localhost:8081")
public interface CompanyServiceClient {

    @GetMapping("/api/company/projects")
    List<ProjectDTO> getAllProjects();

    @GetMapping("/api/company/projects/{id}")
    ProjectDTO getProjectById(@PathVariable("id") Long id);
}
