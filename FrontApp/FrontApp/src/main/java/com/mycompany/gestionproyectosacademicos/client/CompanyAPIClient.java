package com.mycompany.gestionproyectosacademicos.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
}
