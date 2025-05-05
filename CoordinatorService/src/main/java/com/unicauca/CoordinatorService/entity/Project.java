package com.unicauca.CoordinatorService.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String summary;
    private String goals;
    private String description;
    private Integer maxtimeMonths;
    private double budget;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private String comments;

    // Relación con empresa (por ejemplo vía ID o DTO externo)
    private Long companyId;

    // Atributo para saber a quién está asignado el proyecto
    private Long assignedTo;
}
