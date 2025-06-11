package com.unicauca.CoordinatorService.infraestructure.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class JpaProjectEntity {
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
    private JpaProjectStatusEntity status;

    private String comments;

    // Relación con empresa (por ejemplo vía ID o DTO externo)
    private String companyId;

    // Atributo para saber a quién está asignado el proyecto
    private Long assignedTo;
}
