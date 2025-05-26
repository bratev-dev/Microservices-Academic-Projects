package com.unicauca.CoordinatorService.infraestructure;

import com.unicauca.CoordinatorService.domain.model.Project;
import com.unicauca.CoordinatorService.domain.model.ProjectStatus;
import com.unicauca.CoordinatorService.domain.state.*;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.entity.JpaProjectStatusEntity;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.JpaProjectSpringDataRepository;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.ProjectRepositoryImpl;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.ProjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectRepositoryImplTest {

    @Mock
    private JpaProjectSpringDataRepository mockJpaRepository;

    private ProjectRepositoryImpl projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectRepository = new ProjectRepositoryImpl(mockJpaRepository);
    }

    @Test
    void shouldFindByStatus() {
        // Given
        JpaProjectStatusEntity status = JpaProjectStatusEntity.APPROVED;
        List<JpaProjectEntity> jpaEntities = Arrays.asList(
                JpaProjectEntity.builder().id(1L).name("Project 1").status(JpaProjectStatusEntity.APPROVED).build(),
                JpaProjectEntity.builder().id(2L).name("Project 2").status(JpaProjectStatusEntity.APPROVED).build()
        );
        when(mockJpaRepository.findByStatus(status.name())).thenReturn(jpaEntities);

        // When
        List<Project> projects = projectRepository.findByStatus(status.name());

        // Then
        assertEquals(2, projects.size());
        assertEquals("Project 1", projects.get(0).getName());
        assertEquals(ProjectStatus.APPROVED, projects.get(0).getStatus());
        assertInstanceOf(ApprovedState.class, projects.get(0).getState());
        verify(mockJpaRepository, times(1)).findByStatus(status.name());
    }

    @Test
    void shouldFindByStatusNot() {
        // Given
        JpaProjectStatusEntity excludedStatus = JpaProjectStatusEntity.REJECTED;
        List<JpaProjectEntity> jpaEntities = Arrays.asList(
                JpaProjectEntity.builder().id(1L).name("Project 1").status(JpaProjectStatusEntity.APPROVED).build(),
                JpaProjectEntity.builder().id(2L).name("Project 2").status(JpaProjectStatusEntity.ASSIGNED).build()
        );

        when(mockJpaRepository.findByStatusNot(excludedStatus.name())).thenReturn(jpaEntities);

        // When
        List<Project> projects = projectRepository.findByStatusNot(excludedStatus.name());

        // Then
        assertEquals(2, projects.size());
        assertEquals(ProjectStatus.APPROVED, projects.get(0).getStatus());
        assertInstanceOf(ApprovedState.class, projects.get(0).getState());
        assertEquals(ProjectStatus.ASSIGNED, projects.get(1).getStatus());
        assertInstanceOf(AssignedState.class, projects.get(1).getState());
        verify(mockJpaRepository, times(1)).findByStatusNot(excludedStatus.name());
    }

    @Test
    void shouldFindById() {
        // Given
        Long projectId = 1L;
        JpaProjectEntity jpaEntity = JpaProjectEntity.builder()
                .id(projectId)
                .name("Project 1")
                .status(JpaProjectStatusEntity.PENDING)
                .build();

        when(mockJpaRepository.findById(projectId)).thenReturn(Optional.of(jpaEntity));

        // When
        Optional<Project> result = projectRepository.findById(projectId);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Project 1", result.get().getName());
        assertEquals(ProjectStatus.PENDING, result.get().getStatus());
        assertInstanceOf(PendingState.class, result.get().getState());
        verify(mockJpaRepository, times(1)).findById(projectId);
    }

    @Test
    void shouldChangeStatusFromPendingToApproved() {
        // Given
        Long projectId = 1L;
        ProjectStatus initialStatus = ProjectStatus.PENDING;
        ProjectState newState = new ApprovedState();

        JpaProjectEntity existingEntity = JpaProjectEntity.builder()
                .id(projectId)
                .name("Project 1")
                .status(JpaProjectStatusEntity.PENDING) // Estado inicial en JPA
                .build();

        Project domainProject = new Project(
                projectId, "Project 1", "Summary", "Goals", "Description",
                12, 1000.0, LocalDate.now(), "Comments", 1L, 1L, initialStatus
        );

        // Mock del repositorio: encontrar el proyecto existente
        when(mockJpaRepository.findById(projectId)).thenReturn(Optional.of(existingEntity));

        // Mock del guardado del proyecto al cambiar el estado
        when(mockJpaRepository.save(any(JpaProjectEntity.class)))
                .thenAnswer(invocation -> {
                    JpaProjectEntity savedEntity = invocation.getArgument(0);
                    assertEquals(JpaProjectStatusEntity.APPROVED, savedEntity.getStatus()); // Aprobado
                    return savedEntity;
                });

        // When
        projectRepository.changeStatus(projectId, newState);

        // Then
        verify(mockJpaRepository, times(1)).findById(projectId);
        verify(mockJpaRepository, times(1)).save(any(JpaProjectEntity.class));
    }
    @Test
    void shouldChangeStatusFromApprovedToRejected() {
        // Given
        Long projectId = 2L;
        ProjectStatus initialStatus = ProjectStatus.APPROVED;
        ProjectState newState = new RejectState();

        JpaProjectEntity existingEntity = JpaProjectEntity.builder()
                .id(projectId)
                .name("Project 2")
                .status(JpaProjectStatusEntity.APPROVED) // Estado inicial en JPA
                .build();

        Project domainProject = new Project(
                projectId, "Project 2", "Summary", "Goals", "Description",
                12, 1000.0, LocalDate.now(), "Comments", 1L, 1L, initialStatus
        );

        // Mock del repositorio: encontrar el proyecto existente
        when(mockJpaRepository.findById(projectId)).thenReturn(Optional.of(existingEntity));

        // Mock del guardado del proyecto al cambiar el estado
        when(mockJpaRepository.save(any(JpaProjectEntity.class)))
                .thenAnswer(invocation -> {
                    JpaProjectEntity savedEntity = invocation.getArgument(0);
                    assertEquals(JpaProjectStatusEntity.REJECTED, savedEntity.getStatus()); // Rechazado
                    return savedEntity;
                });

        // When
        projectRepository.changeStatus(projectId, newState);

        // Then
        verify(mockJpaRepository, times(1)).findById(projectId);
        verify(mockJpaRepository, times(1)).save(any(JpaProjectEntity.class));
    }
}
