package com.mycompany.gestionproyectosacademicos.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.gestionproyectosacademicos.entities.Student;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentAPIClient {

    private static final String BASE_URL = "http://localhost:8084/api/students"; // Cambia el puerto si usas otro

    // Método para enviar la postulación
    public static boolean applyToProject(int studentId, int projectId) {
        try {
            URL url = new URL(BASE_URL + "/" + studentId + "/postulate/" + projectId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();
            System.out.println("Respuesta POST /postulate: " + responseCode);

            if (responseCode != 200) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    System.err.println("Error response body: " + response.toString());
                } catch (Exception e) {
                    // Ignorar si no hay cuerpo de error
                }
            }

            return responseCode == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public Student login(String email, String password) {
        try {
            URL url = new URL(BASE_URL + "/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes("utf-8"));
            }

            if (conn.getResponseCode() == 200) {
                try (InputStream is = conn.getInputStream()) {
                    ObjectMapper mapper = new ObjectMapper();
                    return mapper.readValue(is, Student.class);
                }
            } else {
                return null; // Credenciales incorrectas
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
