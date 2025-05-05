package com.mycompany.gestionproyectosacademicos.dto;

/**
 *
 * @author jpala
 */
import java.time.LocalDate;

public class ProjectDTO {
    private String name;
    private int id;
    private String description;
    private String summary;
    private String companyName;
    private String status;
    
    public ProjectDTO(String name, int id, String description, String summary, String companyName, String status) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.summary = summary;
        this.companyName = companyName;
        this.status = status;
    }

    // Getters (y setters, si lo necesitas)
    public String getName() { return name; }
    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getSummary() { return summary; }
    public String getCompanyName() { return companyName; }
    public String getStatus() { return status; }
}

