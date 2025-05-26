package com.unicauca.CoordinatorService.presentation.dto;

import com.unicauca.CoordinatorService.oldCode.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
