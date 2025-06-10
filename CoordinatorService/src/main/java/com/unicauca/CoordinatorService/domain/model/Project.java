package com.unicauca.CoordinatorService.domain.model;

import com.unicauca.CoordinatorService.domain.state.ProjectState;
import java.time.LocalDate;
import java.util.Objects;
/**
 *
 * @author jpala
 */
public class Project {
    private Long id;
    private String name;
    private String summary;
    private String goals;
    private String description;
    private Integer maxtimeMonths;
    private double budget;
    private LocalDate date;
    private String comments;

    private String companyId;
    private Long assignedTo;

    private ProjectStatus status; // Valor serializable
    private ProjectState state;   // Comportamiento

    public Project(Long id, String name, String summary, String goals, String description,
                   Integer maxtimeMonths, double budget, LocalDate date, String comments,
                   String companyId, Long assignedTo, ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.goals = goals;
        this.description = description;
        this.maxtimeMonths = maxtimeMonths;
        this.budget = budget;
        this.date = date;
        this.comments = comments;
        this.companyId = companyId;
        this.assignedTo = assignedTo;
        this.setStatus(status);
    }

    public void markAsReceived() {
        state.received(this);
    }

    public void accept() {
        state.accepted(this);
    }

    public void reject() {
        state.rejected(this);
    }

    public void startProgress() {
        state.inProgress(this);
    }

    public void close() {
        state.closed(this);
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
        this.state = status.getState(); // Aquí conectamos enum con objeto de estado
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getGoals() {
        return goals;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMaxtimeMonths() {
        return maxtimeMonths;
    }

    public double getBudget() {
        return budget;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getComments() {
        return comments;
    }

    public String getCompanyId() {
        return companyId;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public ProjectState getState() {
        return state;
    }
}
