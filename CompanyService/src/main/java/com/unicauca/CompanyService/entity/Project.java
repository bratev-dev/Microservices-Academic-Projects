package com.unicauca.CompanyService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.CompanyService.state.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {

    @Id
    private int id;
    private String name;
    private String summary;
    private String goals;
    private String description;
    private String maxTimeInMonths;
    private String budget;
    private String date;

    private String state;  // Persisted state (String)
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private String comments;
    private int calificacion;
    private String request;

    @Transient
    @JsonIgnore
    private ProjectState projectState; // Transient state object

    // Getter personalizado que reconstruye el objeto del estado
    public ProjectState getProjectState() {
        if (projectState == null) {
            switch (state) {
                case "Approved" -> projectState = new ApprovedState();
                case "Rejected" -> projectState = new RejectedState();
                case "Assigned" -> projectState = new AssignedState();
                case "Completed" -> projectState = new CompletedState();
                default -> projectState = new PendingState();
            }
        }
        return projectState;
    }

    // Método para establecer el estado y actualizar la cadena
    public void setProjectState(ProjectState state) {
        this.projectState = state;
        this.state = state.getClass().getSimpleName().replace("State", ""); // Update the String state
        System.out.println("Project: setProjectState called, state set to: " + this.state);
    }

    // State transition methods - delegate to the state object
    public void approve() {
        System.out.println("Project: approve() called");
        getProjectState().approve(this);
    }

    public void reject() {
        System.out.println("Project: reject() called");
        getProjectState().reject(this);
    }

    public void assign() {
        System.out.println("Project: assign() called");
        getProjectState().assign(this);
    }

    public void complete() {
        System.out.println("Project: complete() called");
        getProjectState().complete(this);
    }
}