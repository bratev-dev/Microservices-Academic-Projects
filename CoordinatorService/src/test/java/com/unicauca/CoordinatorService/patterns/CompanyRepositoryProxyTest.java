package com.unicauca.CoordinatorService.patterns;

import com.unicauca.CoordinatorService.adapter.out.proxy.CompanyRepositoryProxy;
import com.unicauca.CoordinatorService.application.port.out.CompanyRepositoryPort;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CompanyRepositoryProxyTest {

    private CompanyRepositoryPort target;
    private CompanyRepositoryProxy proxy;

    @BeforeEach
    void setUp() {
        target = mock(CompanyRepositoryPort.class); // simula el Feign
        proxy = new CompanyRepositoryProxy(target); // el proxy real
    }

    @Test
    void shouldDelegateCallToGetProjectById() {
        // Arrange
        Long projectId = 1L;
        JpaProjectEntity fakeProject = new JpaProjectEntity();
        fakeProject.setId(projectId);

        when(target.getProjectById(projectId)).thenReturn(fakeProject);

        // Act
        JpaProjectEntity result = proxy.getProjectById(projectId);

        // Assert
        verify(target, times(1)).getProjectById(projectId); // verifica delegación
        assertEquals(projectId, result.getId());
    }

    @Test
    void shouldDelegateChangeProjectStatus() {
        Long id = 1L;
        JpaProjectStatusEntity status = JpaProjectStatusEntity.ACCEPTED;
        JpaProjectEntity updated = new JpaProjectEntity();
        updated.setStatus(status);

        when(target.changeProjectStatus(id, status)).thenReturn(updated);

        JpaProjectEntity result = proxy.changeProjectStatus(id, status);

        verify(target).changeProjectStatus(id, status);
        assertEquals(status, result.getStatus());
    }
}