package com.unicauca.CoordinatorService.patterns;

import com.unicauca.CoordinatorService.adapter.out.mapper.EvaluationRequestAdapterImpl;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.presentation.dto.EvaluationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluationRequestAdapterImplTest {

    private EvaluationRequestAdapterImpl adapter;

    @BeforeEach
    void setUp() {
        adapter = new EvaluationRequestAdapterImpl();
    }

    @Test
    void shouldApplyChangesFromRequestToEntity() {
        // Arrange
        JpaProjectEntity entity = new JpaProjectEntity();
        entity.setComments("Viejo comentario");
        entity.setAssignedTo(1L);

        EvaluationRequest request = new EvaluationRequest();
        request.setComments("Nuevo comentario");
        request.setAssignedTo(2L);

        // Act
        adapter.applyChanges(entity, request);

        // Assert
        assertEquals("Nuevo comentario", entity.getComments());
        assertEquals(2L, entity.getAssignedTo());
    }

    @Test
    void shouldIgnoreNullFieldsInRequest() {
        // Arrange
        JpaProjectEntity entity = new JpaProjectEntity();
        entity.setComments("Comentario original");
        entity.setAssignedTo(1L);

        EvaluationRequest request = new EvaluationRequest();
        request.setComments(null);  // campo no editado
        request.setAssignedTo(2L);

        // Act
        adapter.applyChanges(entity, request);

        // Assert
        assertEquals("Comentario original", entity.getComments()); // no cambia
        assertEquals(2L, entity.getAssignedTo());  // sí cambia
    }
}
