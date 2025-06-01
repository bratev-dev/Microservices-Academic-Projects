package com.unicauca.CoordinatorService.adapter.out.persistence;

import com.unicauca.CoordinatorService.application.port.out.CompanyServiceClient;
import com.unicauca.CoordinatorService.application.port.out.CompanyServicePort;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignCompanyServiceClient implements CompanyServicePort {

    private final CompanyServiceClient companyServiceClient;

    public FeignCompanyServiceClient(CompanyServiceClient companyServiceClient) {
        this.companyServiceClient = companyServiceClient;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return companyServiceClient.getAllProjects();
    }
}
