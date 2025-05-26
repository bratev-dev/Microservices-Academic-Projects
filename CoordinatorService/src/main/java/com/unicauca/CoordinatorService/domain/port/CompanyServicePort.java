package com.unicauca.CoordinatorService.domain.port;

import com.unicauca.CoordinatorService.oldCode.infra.dto.ProjectDTO;
import java.util.List;

public interface CompanyServicePort {
    List<ProjectDTO> getAllProjects();
}