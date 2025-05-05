package com.mycompany.gestionproyectosacademicos.client;

import com.mycompany.gestionproyectosacademicos.dto.ProjectDTO;
import java.io.*;
import java.net.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
/**
 *
 * @author jpala
 */
public class ProjectAPIClient {

    public static ProjectDTO getProjectById(int projectId) throws IOException {
        String apiUrl = "http://localhost:8081/projects/" + projectId; // Usa la URL correcta
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String responseJson = reader.lines().collect(Collectors.joining());
                // Usa alguna librería como Jackson o Gson para parsear:
                return new Gson().fromJson(responseJson, ProjectDTO.class);
            }
        } else {
            throw new IOException("Error al obtener proyecto, código HTTP: " + connection.getResponseCode());
        }
    }
}

