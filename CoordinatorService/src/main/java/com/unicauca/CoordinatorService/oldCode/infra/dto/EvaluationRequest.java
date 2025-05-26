package com.unicauca.CoordinatorService.oldCode.infra.dto;

import com.unicauca.CoordinatorService.oldCode.entity.ProjectStatus;
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
