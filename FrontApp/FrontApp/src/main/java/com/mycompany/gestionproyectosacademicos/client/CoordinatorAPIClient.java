package com.mycompany.gestionproyectosacademicos.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.gestionproyectosacademicos.dto.ProjectDTO;

public class CoordinatorAPIClient {

    public static ProjectDTO getProjectById(int id) {
        try {
            String responseJson = ApiClient.get("/coordinator/api/projects/" + id); // Ruta vía API Gateway
            if (responseJson == null) return null;

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseJson, ProjectDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
