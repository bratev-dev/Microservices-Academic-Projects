
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionproyectosacademicos.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.gestionproyectosacademicos.entities.Project;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ProjectRepositoryMS implements IProjectRepository {

    public ProjectRepositoryMS() {
    }
    private final String apiUrl = "http://localhost:8082/api/projects";
    private static List<Project> projects;

    public List<Project> getAllProjects() {
        // 1. Si ya hay proyectos cargados, retórnalos
        if (this.projects != null && !this.projects.isEmpty()) {
            return this.projects;
        }

        // 2. Lógica de carga desde API
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(apiUrl);

            org.apache.http.HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                this.projects = mapper.readValue(jsonResponse, new TypeReference<List<Project>>() {
                });
                return this.projects;
            } else {
                Logger.getLogger(ProjectRepositoryMS.class.getName())
                        .log(Level.SEVERE, "Error al obtener proyectos. Código: {0}", response.getStatusLine().getStatusCode());
            }
        } catch (IOException ex) {
            Logger.getLogger(ProjectRepositoryMS.class.getName()).log(Level.SEVERE, "Error en la conexión", ex);
        }

        return new ArrayList<>(); // Retorna lista vacía si hay error
    }

    @Override
    public boolean evaluateProject(Long projectId, String newStatus) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = apiUrl + "/" + projectId + "/evaluate";
            HttpPut request = new HttpPut(url);

            String requestBody = "{\"status\":\"" + newStatus + "\"}";
            request.setEntity(new StringEntity(requestBody));
            request.setHeader("Content-Type", "application/json");

            org.apache.http.HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                // Actualizar el estado en la lista local
                if (projects != null) {
                    projects.stream()
                            .filter(p -> p.getId().equals(projectId))
                            .findFirst()
                            .ifPresent(p -> p.setStatus(newStatus));
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            Logger.getLogger(ProjectRepositoryMS.class.getName()).log(Level.SEVERE, "Error al evaluar proyecto", e);
            return false;
        }
    }

    @Override
    public Project getProjectById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /*@Override
    public void create(Project product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(int id, Project productUpdated) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    */
    
    @Override
public void saveProject(Project project) {
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        // Configurar la petición POST
        HttpPost request = new HttpPost("http://localhost:8081/api/projects");
        request.setHeader("Content-Type", "application/json");
        
        // Crear DTO compatible con el microservicio
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        
        // Convertir el proyecto a JSON compatible
        String jsonProject = createProjectJson(project);
        request.setEntity(new StringEntity(jsonProject));
        
        // Ejecutar la petición
        org.apache.http.HttpResponse response = httpClient.execute(request);
        
        // Procesar la respuesta
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            String responseBody = EntityUtils.toString(response.getEntity());
            Project savedProject = mapper.readValue(responseBody, Project.class);
            
            // Actualizar la lista local
            if (projects == null) {
                projects = new ArrayList<>();
            }
            projects.add(savedProject);
            
            Logger.getLogger(ProjectRepositoryMS.class.getName())
                .log(Level.INFO, "Proyecto guardado exitosamente con ID: {0}", savedProject.getId());
        } else {
            String errorResponse = EntityUtils.toString(response.getEntity());
            Logger.getLogger(ProjectRepositoryMS.class.getName())
                .log(Level.SEVERE, "Error al guardar proyecto. Código: {0}, Respuesta: {1}", 
                    new Object[]{response.getStatusLine().getStatusCode(), errorResponse});
            throw new RuntimeException("Error al guardar proyecto: " + errorResponse);
        }
    } catch (IOException ex) {
        Logger.getLogger(ProjectRepositoryMS.class.getName())
            .log(Level.SEVERE, "Error de conexión al guardar proyecto", ex);
        throw new RuntimeException("Error de conexión con el microservicio", ex);
    }
}

private String createProjectJson(Project project) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> projectMap = new HashMap<>();
    
    // Mapear campos compatibles
    projectMap.put("name", project.getName());
    projectMap.put("summary", project.getSummary());
    projectMap.put("goals", project.getGoals());
    projectMap.put("description", project.getDescription());
    projectMap.put("maxtimeMonths", project.getMaxTimeInMonths());
    projectMap.put("budget", project.getBudget());
    projectMap.put("date", project.getDate()); // Ya está en formato "yyyy-MM-dd"
    projectMap.put("status", project.getStatus());
    projectMap.put("comments", project.getComments());
    projectMap.put("companyId", project.getCompanyId());
    projectMap.put("assignedTo", project.getAssignedTo());
    
    return mapper.writeValueAsString(projectMap);
}

    @Override
    public List<Project> getProjectsByAcademicPeriod(String academicPeriod) {
        List<Project> filteredProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getAcademicPeriod().equals(academicPeriod)) {
                filteredProjects.add(project);
            }
        }
        return filteredProjects;
    }

    @Override
    public void deleteProject(int projectId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existsProject(String nit, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNextProjectId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existsCompany(String nit, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}