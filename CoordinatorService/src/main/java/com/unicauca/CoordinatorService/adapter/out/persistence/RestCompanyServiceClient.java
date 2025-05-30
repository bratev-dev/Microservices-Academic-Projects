package com.unicauca.CoordinatorService.adapter.out.persistence;

import com.unicauca.CoordinatorService.application.port.out.CompanyServicePort;
import com.unicauca.CoordinatorService.presentation.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class RestCompanyServiceClient implements CompanyServicePort {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8082/api/projects";

    public RestCompanyServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        ResponseEntity<ProjectDTO[]> response = restTemplate.getForEntity(baseUrl, ProjectDTO[].class);
        return Arrays.asList(response.getBody());
    }
}
