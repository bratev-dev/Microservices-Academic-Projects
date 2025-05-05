package com.mycompany.gestionproyectosacademicos.client;

import java.net.HttpURLConnection;
import java.net.URL;

public class StudentAPIClient {

    private static final String BASE_URL = "http://localhost:8081/api/students"; // Cambia el puerto si usas otro

    // Método para enviar la postulación
    public static boolean applyToProject(int studentId, int projectId) {
        try {
            // Llama al endpoint correcto del controlador
            URL url = new URL(BASE_URL + "/" + studentId + "/postulate/" + projectId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // Aunque no envíes un body, es necesario en algunos servidores

            int responseCode = conn.getResponseCode();
            return responseCode >= 200 && responseCode < 300;

        } catch (Exception e) {
            System.err.println("Error al aplicar al proyecto: " + e.getMessage());
            return false;
        }
    }
}
