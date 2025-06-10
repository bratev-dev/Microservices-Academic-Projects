package com.mycompany.gestionproyectosacademicos.dto;

/**
 *
 * @author jpala
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.gestionproyectosacademicos.client.CompanyAPIClient;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDTO {
    private String name;
    private int id;
    private String description;
    private String summary;
    private String companyId;
    private String companyName;
    private String status;
    
    // Constructor vacío necesario para Jackson
    public ProjectDTO() {
    }
    
    public ProjectDTO(String name, int id, String description, String summary, String companyId, String companyName, String status) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.summary = summary;
        this.companyId = companyId;
        this.companyName = companyName;
        this.status = status;
    }

    // Getters 
    public String getName() { return name; }
    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getSummary() { return summary; }
    public String getCompanyId() {return companyId; }
    public String getCompanyName() {
        String companyName = null;
        companyName = CompanyAPIClient.getCompanyNameById(companyId);
        System.out.println("Nombre de la empresa recuperado: " + companyName);
        return companyName; }
    public String getStatus() { return status; }
}

