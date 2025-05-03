package com.unicauca.StudentService.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@Component
public class StudentAPIClient {

    private final RestTemplate restTemplate;

    public StudentAPIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean notifyCoordinatorOfPostulation(int studentId, int projectId) {
        String coordinatorUrl = "http://coordinator-service/api/coordinator/notify-postulation";

        // Crear cuerpo JSON con los datos
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("studentId", studentId);
        requestBody.put("projectId", projectId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(coordinatorUrl, requestEntity, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.err.println("Error al notificar al coordinador: " + e.getMessage());
            return false;
        }
    }
}
