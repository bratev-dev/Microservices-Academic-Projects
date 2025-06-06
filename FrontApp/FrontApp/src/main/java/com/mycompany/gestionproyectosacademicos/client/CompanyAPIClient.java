package com.mycompany.gestionproyectosacademicos.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.gestionproyectosacademicos.dto.ProjectDTO;

public class CompanyAPIClient {

    private static final String BASE_PATH = "/company/api/companies";

    public static String getCompanyNameById(Long companyId) {
        String endpoint = BASE_PATH + "/nombre/" + companyId;
        String json = ApiClient.get(endpoint);

        if (json == null) {
            return "Error al conectar con el servicio de empresas o token inválido";
        }

        // Si el servicio retorna 404, el ApiClient devolverá el body (probablemente vacío o mensaje)
        if (json.contains("No se encontró") || json.contains("404")) {
            return "Empresa no encontrada";
        }

        return json;
    }

    public static ProjectDTO getProjectById(int id) {
        String endpoint = BASE_PATH + "/" + id;
        String json = ApiClient.get(endpoint);

        if (json == null) return null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, ProjectDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
