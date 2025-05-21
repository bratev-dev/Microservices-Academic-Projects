package com.mycompany.gestionproyectosacademicos.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.gestionproyectosacademicos.state.ProjectState;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.gestionproyectosacademicos.observer.IObserver;
import java.time.format.DateTimeFormatter;
import lombok.Data;

public class Project {
    
    private Long id;
    private String name;
    private String summary;
    private String goals;
    private String description;
    private Integer maxtimeMonths;
    private double budget;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date;
    
    private String status;
    //private Company company;
    private String comments;
    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxtimeMonths() {
        return maxtimeMonths;
    }

    public void setMaxtimeMonths(Integer maxtimeMonths) {
        this.maxtimeMonths = maxtimeMonths;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }
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
    
    
}
