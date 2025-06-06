package com.unicauca.CompanyService.service;

import com.unicauca.CompanyService.dto.ProjectDTO;
import com.unicauca.CompanyService.entity.Project;
import com.unicauca.CompanyService.entity.ProjectStatus;
import com.unicauca.CompanyService.repository.ProjectRepository;
import com.unicauca.CompanyService.state.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    // --- Inicio: Implementación del Patrón State ---

    // Usar un mapa para gestionar instancias singleton de los estados (opcional pero recomendado)
    private final Map<ProjectStatus, ProjectState> stateCache = new ConcurrentHashMap<>();

    // Método para obtener la instancia del estado correspondiente
    private ProjectState getState(ProjectStatus status) {
        return stateCache.computeIfAbsent(status, s -> {
            switch (s) {
                case RECEIVED: return new ReceivedState();
                case ACCEPTED: return new AcceptedState();
                case REJECTED: return new RejectedState();
                case IN_PROGRESS: return new InProgressState();
                case CLOSED: return new ClosedState();
                default: throw new IllegalArgumentException("Unknown project status: " + s);
            }
        });
    }

    public Map<String, Long> countProjectsByState() {
        List<Project> allProjects = getAllProjects(); // Aquí se hace la petición al microservicio Company

        // Agrupar por el nombre del estado (puede depender de cómo implementaste el patrón State)
        return allProjects.stream()
                .collect(Collectors.groupingBy(
                        project -> project.getStatus().toString(), // o .toString() si no tienes getName()
                        Collectors.counting()
                ));
    }

    // Método genérico para aplicar una acción de estado
    // Usará Transactional para asegurar que la búsqueda y guardado sean atómicos
    @Transactional
    protected String applyStateAction(Long id, java.util.function.Consumer<ProjectState> action, Project project) {
        try {
            ProjectState currentState = getState(project.getStatus());
            action.accept(currentState); // Ejecuta la acción (approve, reject, etc.) en el estado actual
            repository.save(project); // Guarda el proyecto con el estado potencialmente cambiado
            return "✅ Acción completada. Estado actual: " + project.getStatus();
        } catch (IllegalStateException e) {
            // Captura excepciones de transiciones inválidas
            System.err.println("Error cambiando estado para proyecto " + id + ": " + e.getMessage());
            return "❌ Acción inválida: " + e.getMessage();
        } catch (Exception e) {
            // Captura otros errores inesperados
            System.err.println("Error inesperado cambiando estado para proyecto " + id + ": " + e.getMessage());
            return "❌ Error inesperado al procesar la acción.";
        }
    }
    // --- Fin: Implementación del Patrón State ---
    @Transactional
    public Project createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setSummary(projectDTO.getSummary());
        project.setGoals(projectDTO.getGoals());
        project.setDescription(projectDTO.getDescription());
        project.setMaxtimeMonths(projectDTO.getMaxtimeMonths());
        project.setBudget(projectDTO.getBudget());
        project.setDate(projectDTO.getDate());
        // Elimina el bloque try-catch para el status del DTO

        // Asigna SIEMPRE el estado RECEIVED al crear
        project.setStatus(ProjectStatus.RECEIVED); // Estado inicial fijo

        project.setComments(projectDTO.getComments());
        project.setCompanyId(projectDTO.getCompanyId());
        project.setAssignedTo(projectDTO.getAssignedTo());

        Project savedProject = repository.save(project);
        System.out.println("Proyecto guardado en BD con ID: " + savedProject.getId()); // Debug
        return savedProject;
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Optional<Project> getProjectById(Long id) { // Cambiado a Long [cite: 1]
        return repository.findById(id);
    }

    @Transactional
    public Project updateProject(Long id, ProjectDTO updatedProjectDTO) {
        Optional<Project> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Project not found with id: " + id);
        }

        Project existingProject = optional.get();
        ProjectStatus currentStatus = existingProject.getStatus();

        // Actualizar campos básicos (excepto estado)
        if (updatedProjectDTO.getName() != null) {
            existingProject.setName(updatedProjectDTO.getName());
        }
        if (updatedProjectDTO.getSummary() != null) {
            existingProject.setSummary(updatedProjectDTO.getSummary());
        }
        if (updatedProjectDTO.getGoals() != null) {
            existingProject.setGoals(updatedProjectDTO.getGoals());
        }
        if (updatedProjectDTO.getDescription() != null) {
            existingProject.setDescription(updatedProjectDTO.getDescription());
        }
        if (updatedProjectDTO.getMaxtimeMonths() != null) {
            existingProject.setMaxtimeMonths(updatedProjectDTO.getMaxtimeMonths());
        }
        // Asegúrate de verificar null para todos los campos que pueden actualizarse
        if (updatedProjectDTO.getBudget() != 0) { // O la condición apropiada para budget
            existingProject.setBudget(updatedProjectDTO.getBudget());
        }
        if (updatedProjectDTO.getDate() != null) {
            existingProject.setDate(updatedProjectDTO.getDate());
        }
        if (updatedProjectDTO.getComments() != null) {
            existingProject.setComments(updatedProjectDTO.getComments());
        }
        if (updatedProjectDTO.getCompanyId() != null) {
            existingProject.setCompanyId(updatedProjectDTO.getCompanyId());
        }
        if (updatedProjectDTO.getAssignedTo() != null) {
            existingProject.setAssignedTo(updatedProjectDTO.getAssignedTo());
        }


        // Manejo simplificado para el estado con PUT
        if (updatedProjectDTO.getStatus() != null) {
            try {
                ProjectStatus requestedStatus = ProjectStatus.valueOf(updatedProjectDTO.getStatus().toUpperCase());

                if (requestedStatus != currentStatus) {
                    ProjectState currentState = getState(currentStatus);

                    // Validar si la transición directa a 'requestedStatus' es válida desde 'currentState'
                    if (currentState.isValidNextState(requestedStatus)) {
                        // Si la transición es válida, simplemente cambia el estado
                        existingProject.setStatus(requestedStatus);
                        System.out.println("Estado del proyecto " + id + " cambiado directamente a " + requestedStatus + " vía PUT.");
                    } else {
                        // Si no es válida, lanza una excepción
                        throw new IllegalStateException("Transición de estado inválida desde " + currentStatus + " a " + requestedStatus + " mediante PUT.");
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Valor de estado inválido proporcionado: " + updatedProjectDTO.getStatus());
            } catch (IllegalStateException e) {
                // Re-lanzar la excepción de estado inválido para que sea manejada (o loggeada)
                throw e;
            }
        }

        return repository.save(existingProject);
    }

    public boolean deleteProject(Long id) {  // Cambiado a Long [cite: 4, 5, 6]
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private String changeState(Long id, ProjectStatus newStatus) {  // Cambiado a Long [cite: 6, 7, 8]
        Optional<Project> optional = repository.findById(id);
        if (optional.isPresent()) {
            Project project = optional.get();
            project.setStatus(newStatus);
            repository.save(project);
            return "✅ Estado actual: " + project.getStatus();
        }
        return "❌ Proyecto no encontrado";
    }
    // Método auxiliar para encontrar el proyecto o devolver mensaje de error
    private Optional<Project> findProjectOrReturnError(Long id) {
        Optional<Project> optional = repository.findById(id);
        if (!optional.isPresent()) {
            System.err.println("Proyecto no encontrado con ID: " + id);
        }
        return optional;
    }
    public String approveProject(Long id) {
        Optional<Project> optionalProject = findProjectOrReturnError(id);
        // Si el proyecto existe, aplica la acción 'approve' usando el estado actual
        return optionalProject.map(project ->
                applyStateAction(id, state -> state.approve(project), project)
        ).orElse("❌ Proyecto no encontrado con ID: " + id); // Mensaje si no se encontró
    }

    public String rejectProject(Long id) {
        Optional<Project> optionalProject = findProjectOrReturnError(id);
        return optionalProject.map(project ->
                applyStateAction(id, state -> state.reject(project), project)
        ).orElse("❌ Proyecto no encontrado con ID: " + id);
    }

    public String assignProject(Long id) {
        Optional<Project> optionalProject = findProjectOrReturnError(id);
        return optionalProject.map(project ->
                applyStateAction(id, state -> state.assign(project), project)
        ).orElse("❌ Proyecto no encontrado con ID: " + id);
    }

    public String completeProject(Long id) {
        Optional<Project> optionalProject = findProjectOrReturnError(id);
        return optionalProject.map(project ->
                applyStateAction(id, state -> state.complete(project), project)
        ).orElse("❌ Proyecto no encontrado con ID: " + id);
    }

    // Cambiado nombre de 'pendingProject' a 'markProjectAsReceived' para claridad
    public String markProjectAsReceived(Long id) {
        Optional<Project> optionalProject = findProjectOrReturnError(id);
        return optionalProject.map(project ->
                applyStateAction(id, state -> state.markAsReceived(project), project)
        ).orElse("❌ Proyecto no encontrado con ID: " + id);
    }
}