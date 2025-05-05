package com.mycompany.gestionproyectosacademicos.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.gestionproyectosacademicos.state.ProjectState;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.gestionproyectosacademicos.observer.IObserver;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class Project {
    
    private Long id;
    private String name;
    private String summary;
    private String goals;
    private String description;
    private Integer maxTimeInMonths;
    private double budget;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    
    private String status;
    //private Company company;
    private String comments;
    private Long companyId;
    private Long assignedTo;
    
    //private int calificacion;
    //private String request;
    //private Student[] students;
    //private List<IObserver> observers = new ArrayList<>();

    public  Project(){
        //Constructor por defecto
    }

    // Método para calcular el período académico
    public String getAcademicPeriod() {
        LocalDate postulationDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int year = postulationDate.getYear();
        int month = postulationDate.getMonthValue();

        if (month >= 1 && month <= 6) {
            return year + "-1"; // Primer semestre
        } else {
            return year + "-2"; // Segundo semestre
        }
    }
    
    /*public Project evaluateProject(String proId, String proStatusStr) {
        Optional<Project> optionalProject = projectRepository.findById(proId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.syncState(); // Sincroniza con el estado actual

            EnumProjectState newState;
            try {
                newState = EnumProjectState.valueOf(proStatusStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidStateTransitionException("Estado inválido: " + proStatusStr);
            }

            // Ejecutar transición de estado usando el patrón State
            switch (newState) {
                case ACEPTADO -> project.getCurrentState().accept(project);
                case RECHAZADO -> project.getCurrentState().reject(project);
                case EJECUCION -> project.getCurrentState().execute(project);
                case CERRADO -> project.getCurrentState().close(project);
                case RECIBIDO -> project.getCurrentState().receive(project);
            }

            projectRepository.save(project);
            rabbitTemplate.convertAndSend(RabbitMQConfig.UPDATEPROJECT_QUEUE, projectService.projectToDto(project));
            return project;
        } else {
            throw new InvalidStateTransitionException("Proyecto no encontrado con ID: " + proId);
        }
    }*/
}
