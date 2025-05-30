package com.unicauca.CoordinatorService.application.port.out;

import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import java.util.List;

public interface CompanyServicePort {
    List<ProjectDTO> getAllProjects();
}
