package com.unicauca.CoordinatorService.adapter.out.mapper;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;
import org.springframework.stereotype.Component;

@Component
public class EvaluationRequestAdapterImpl implements EvaluationRequestAdapter {
    @Override
    public void applyChanges(JpaProjectEntity target, EvaluationRequest request) {
        if (request.getComments() != null) {
            target.setComments(request.getComments());
        }
        if (request.getAssignedTo() != null) {
            target.setAssignedTo(request.getAssignedTo());
        }
    }
}

