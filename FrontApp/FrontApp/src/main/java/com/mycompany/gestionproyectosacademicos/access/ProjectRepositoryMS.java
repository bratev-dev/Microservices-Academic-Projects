package com.mycompany.gestionproyectosacademicos.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.gestionproyectosacademicos.client.ApiClient;
import com.mycompany.gestionproyectosacademicos.entities.Project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectRepositoryMS implements IProjectRepository {

    private static final String BASE_PATH = "/company/api/projects";
    private static final Logger LOGGER = Logger.getLogger(ProjectRepositoryMS.class.getName());

    private static List<Project> projects;

    @Override
    public List<Project> getAllProjects() {
        if (this.projects != null && !this.projects.isEmpty()) {
            return this.projects;
        }

        try {
            String jsonResponse = ApiClient.get(BASE_PATH);
            if (jsonResponse == null) {
                LOGGER.severe("No se pudo obtener respuesta del API");
                return new ArrayList<>();
            }

            ObjectMapper mapper = new ObjectMapper();
            this.projects = mapper.readValue(jsonResponse, new TypeReference<List<Project>>() {
            });

            return this.projects;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al obtener proyectos", e);
            return new ArrayList<>();
        }
    }

    @Override
    public boolean evaluateProject(Long projectId, String newStatus) {
        try {

            String url = BASE_PATH + "/" + projectId;
            String body = "{\"status\":\"" + newStatus + "\"}";
            System.out.println("Ms evalueProject:  " + url);
            System.out.println("Body: " + body);
            String response = ApiClient.put(url, body);
            if (response != null) {
                if (projects != null) {
                    projects.stream()
                            .filter(p -> p.getId().equals(projectId))
                            .findFirst()
                            .ifPresent(p -> p.setStatus(newStatus));
                }
                return true;
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al evaluar proyecto", e);
        }
        return false;
    }

    @Override
    public Project getProjectById(int id) {
        try {
            String endpoint = BASE_PATH + "/" + id;
            String response = ApiClient.get(endpoint);

            if (response == null) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, Project.class);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al obtener proyecto por ID", e);
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

            String jsonProject = createProjectJson(project);
            System.out.println("JSON enviado al servidor: " + jsonProject); // Debug

            String response = ApiClient.post(BASE_PATH, jsonProject);
            System.out.println("Respuesta del servidor: " + response); // Debug

            if (response == null || response.trim().isEmpty()) {
                throw new RuntimeException("El servidor no devolvió una respuesta válida");
            }

            Project savedProject = mapper.readValue(response, Project.class);
            if (projects == null) {
                projects = new ArrayList<>();
            }
            projects.add(savedProject);

            LOGGER.log(Level.INFO, "Proyecto guardado con ID: {0}", savedProject.getId());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error al guardar proyecto", ex);
            throw new RuntimeException("Error de conexión con el microservicio", ex);
        }
    }

    private String createProjectJson(Project project) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> projectMap = new HashMap<>();

        projectMap.put("name", project.getName());
        projectMap.put("summary", project.getSummary());
        projectMap.put("goals", project.getGoals());
        projectMap.put("description", project.getDescription());
        projectMap.put("maxtimeMonths", project.getMaxtimeMonths());
        projectMap.put("budget", project.getBudget());
        projectMap.put("date", project.getDate());
        projectMap.put("status", project.getStatus());
        projectMap.put("comments", project.getComments());
        projectMap.put("companyId", project.getCompanyId());
        projectMap.put("assignedTo", project.getAssignedTo());

        return mapper.writeValueAsString(projectMap);
    }

    @Override
    public List<Project> getProjectsByAcademicPeriod(String academicPeriod) {
        List<Project> filteredProjects = new ArrayList<>();
        if (projects != null) {
            for (Project project : projects) {
                if (academicPeriod.equals(project.getAcademicPeriod())) {
                    filteredProjects.add(project);
                }
            }
        }
        return filteredProjects;
    }

    @Override
    public Map<String, Long> countProjectsByState() {
        try {
            String jsonResponse = ApiClient.get(BASE_PATH + "/count-by-state");
            if (jsonResponse == null) {
                LOGGER.severe("No se pudo obtener respuesta del API");
                return new HashMap<>();
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResponse, new TypeReference<Map<String, Long>>() {
            });

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al contar proyectos por estado", e);
            return new HashMap<>();
        }
    }
}
