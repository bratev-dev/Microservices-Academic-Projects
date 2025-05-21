package com.unicauca.CoordinatorService.presentation.dto;

import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationRequest {
    private Long projectId;
    private ProjectStatus status;
    private String comments;
    public Long assignedTo;
}
