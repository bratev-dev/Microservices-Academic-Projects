package com.mycompany.gestionproyectosacademicos.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.gestionproyectosacademicos.dto.ProjectDTO;
import java.io.*;
import java.net.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import java.net.http.*;
/**
 *
 * @author jpala
 */
public class CoordinatorAPIClient {
    private static final String BASE_URL = "http://localhost:8082/api/projects";

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


