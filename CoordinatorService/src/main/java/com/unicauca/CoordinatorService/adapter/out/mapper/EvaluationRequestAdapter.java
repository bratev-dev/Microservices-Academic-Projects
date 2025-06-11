package com.unicauca.CoordinatorService.adapter.out.mapper;

import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;

public interface EvaluationRequestAdapter {
    void applyChanges(JpaProjectEntity target, EvaluationRequest request);
}