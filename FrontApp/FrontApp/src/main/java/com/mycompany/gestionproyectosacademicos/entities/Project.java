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
    

    @JsonFormat(pattern = "dd-MM-yyyy")
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

        LocalDate postulationDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        int year = postulationDate.getYear();
        int month = postulationDate.getMonthValue();

        if (month >= 1 && month <= 6) {
            return year + "-1"; // Primer semestre
        } else {
            return year + "-2"; // Segundo semestre
        }
    }
   
}
