package com.unicauca.CoordinatorService.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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
    private ProjectStatus estado;

    private String comentarios;

    // Relación con empresa (por ejemplo vía ID o DTO externo)
    private Long empresaId;
}
