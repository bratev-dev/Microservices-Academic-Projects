package com.unicauca.CoordinatorService.controller;

import com.unicauca.CoordinatorService.infra.dto.ProjectDTO;
import com.unicauca.CoordinatorService.service.CoordinatorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coordinator")
@CrossOrigin
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return coordinatorService.obtenerProyectosPostulados();
    }
}