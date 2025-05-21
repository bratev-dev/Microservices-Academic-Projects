package com.mycompany.gestionproyectosacademicos.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.gestionproyectosacademicos.dto.ProjectDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CompanyAPIClient {

    private static final String BASE_URL = "http://localhost:8081/api/companies"; // Asegúrate de usar el puerto correcto

    public static String getCompanyNameById(Long companyId) {
        try {
            URL url = new URL(BASE_URL + "/nombre/" + companyId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString(); // Devuelve el nombre
                }
            } else if (responseCode == 404) {
                return "Empresa no encontrada";
            } else {
                return "Error al obtener el nombre de la empresa (Código: " + responseCode + ")";
            }

        } catch (Exception e) {
            System.err.println("Error al obtener la empresa: " + e.getMessage());
            return "Error al conectar con el servicio de empresas";
        }
    }
    
    public static ProjectDTO getProjectById(int id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/" + id))
            .header("Accept", "application/json")
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), ProjectDTO.class);
        }

        return null;
    }
}
