package com.unicauca.CoordinatorService.infra.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDTO {

    private Long id;
    private String name;
    private String summary;
    private String description;
    private String status;
    private Long companyId;
    private String date;
}
