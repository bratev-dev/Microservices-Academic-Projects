package com.unicauca.CoordinatorService.presentation.dto;


import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationRequest {
    private Long projectId;
    private JpaProjectStatusEntity status;
    private String comments;
    public Long assignedTo;
}
