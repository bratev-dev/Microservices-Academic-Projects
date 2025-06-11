package com.mycompany.gestionproyectosacademicos.client;

import static com.mycompany.gestionproyectosacademicos.services.AuthService.refreshAccessToken;
import com.mycompany.gestionproyectosacademicos.services.SessionContext;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.HttpClient;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8083"; // ⚠️ API Gateway

    private static boolean ensureValidToken() {
        if (SessionContext.isTokenExpired()) {
            return refreshAccessToken();
        }
        return true;
    }

    public static String get(String endpointPath) {
        if (!ensureValidToken()) {
            return null;
        }

        try {
            HttpGet request = new HttpGet(BASE_URL + endpointPath);
            request.setHeader("Authorization", "Bearer " + SessionContext.getAccessToken());

            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(request);

            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String post(String endpointPath, String jsonBody) {
        if (!ensureValidToken()) {
            return null;
        }

        try {
            HttpPost request = new HttpPost(BASE_URL + endpointPath);
            request.setHeader("Authorization", "Bearer " + SessionContext.getAccessToken());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));

            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(request);

            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String put(String endpointPath, String jsonBody) {
        if (!ensureValidToken()) {
            return null;
        }

        try {
            String fullUrl = BASE_URL + endpointPath;
            System.out.println(">>> Enviando PUT a: " + fullUrl); // Log para depuración

            HttpPut request = new HttpPut(fullUrl);
            request.setHeader("Authorization", "Bearer " + SessionContext.getAccessToken());
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));

            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(request);

            // Verifica el código de estado
            if (response.getStatusLine().getStatusCode() == 404) {
                System.err.println("Error 404 - Revisa la URL: " + fullUrl);
            }

            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Error en PUT: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
