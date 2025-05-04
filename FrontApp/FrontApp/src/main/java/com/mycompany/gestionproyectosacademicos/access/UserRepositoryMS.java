package com.mycompany.gestionproyectosacademicos.access;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mycompany.gestionproyectosacademicos.entities.AuthResponse;
import com.mycompany.gestionproyectosacademicos.entities.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author juand
 */
public class UserRepositoryMS implements IUserRepository {

    @Override
    public User authenticate(String email, String password) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        User user = null;

        try {
            String apiUrl = "http://localhost:8080/api/auth/login";
            String jsonBody = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

            HttpPost request = new HttpPost(apiUrl);
            request.setEntity(new StringEntity(jsonBody));
            request.setHeader("Content-type", "application/json");

            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            String jsonResponse = EntityUtils.toString(response.getEntity());

            // Mapea directamente a AuthResponse
            AuthResponse authResponse = mapper.readValue(jsonResponse, AuthResponse.class);
            if (!authResponse.isSuccess()) {
                return null;
            }

            user = new User();
            user.setId(authResponse.getUserId().intValue());
            user.setEmail(authResponse.getEmail());
            user.setPassword(password); // En general no se recomienda guardar esto en cliente
            user.setRole(authResponse.getRole());

        } catch (Exception e) {
            Logger.getLogger(UserRepositoryMS.class.getName()).log(Level.SEVERE, null, e);
        }

        return user;
    }

    @Override
    public boolean saveUser(int id, String email, String password, String role) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String apiUrl = "http://localhost:8080/api/auth/register";

            // Construir el JSON manualmente
            String jsonBody = String.format(
                    "{\"email\":\"%s\", \"password\":\"%s\", \"role\":\"%s\"}",
                    email, password, role
            );

            HttpPost request = new HttpPost(apiUrl);
            request.setEntity(new StringEntity(jsonBody, "UTF-8"));
            request.setHeader("Content-type", "application/json");

            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            return statusCode == 200 || statusCode == 201; // éxito

        } catch (Exception e) {
            Logger.getLogger(UserRepositoryMS.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public int getUserIdByEmail(String email) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String apiUrl = "http://localhost:8080/api/auth/user/" + email;

            HttpGet request = new HttpGet(apiUrl);
            request.setHeader("Content-type", "application/json");

            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                JsonNode node = mapper.readTree(jsonResponse);

                return node.get("id").asInt(); // Asume que el JSON tiene un campo "id"
            }

        } catch (Exception e) {
            Logger.getLogger(UserRepositoryMS.class.getName()).log(Level.SEVERE, null, e);
        }

        return -1; // No encontrado o error
    }

}
