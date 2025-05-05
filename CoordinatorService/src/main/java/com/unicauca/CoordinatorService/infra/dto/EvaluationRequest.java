package com.unicauca.CoordinatorService.infra.dto;

import com.unicauca.CoordinatorService.entity.ProjectStatus;
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
