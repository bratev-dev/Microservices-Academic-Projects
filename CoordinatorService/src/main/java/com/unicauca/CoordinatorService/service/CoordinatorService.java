package com.unicauca.CoordinatorService.service;

import com.unicauca.CoordinatorService.domain.port.CompanyServicePort;
import com.unicauca.CoordinatorService.infra.dto.ProjectDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoordinatorService {

    private final CompanyServicePort companyService;

    public CoordinatorService(CompanyServicePort companyService) {
        this.companyService = companyService;
    }

    public List<ProjectDTO> obtenerProyectosPostulados() {
        return companyService.getAllProjects();
    }
}