package com.mycompany.gestionproyectosacademicos.access;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.gestionproyectosacademicos.entities.User;
import com.mycompany.gestionproyectosacademicos.services.SessionContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepositoryMS implements IUserRepository {

    private static final String TOKEN_URL = "http://localhost:8080/realms/sistema/protocol/openid-connect/token";
    private static final String CLIENT_ID = "sistema-desktop"; //
    private static final String GRANT_TYPE = "password";

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public User authenticate(String usern, String password) {
        HttpClient httpClient = HttpClients.createDefault();

        try {
            String body = "grant_type=" + GRANT_TYPE
                    + "&client_id=" + CLIENT_ID
                    + "&username=" + usern
                    + "&password=" + password;

            System.out.println("[DEBUG] Request Body: " + body);

            HttpPost request = new HttpPost(TOKEN_URL);
            request.setEntity(new StringEntity(body));
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");

            System.out.println("[DEBUG] Sending request to: " + TOKEN_URL);

            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            System.out.println("[DEBUG] Response Status Code: " + statusCode);

            if (statusCode != 200) {
                System.out.println("[ERROR] Error al autenticar en Keycloak. Código: " + statusCode);
                return null;
            }

            String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println("[DEBUG] Raw Token Response: " + json);

            JsonNode tokenResponse = mapper.readTree(json);
            String accessToken = tokenResponse.get("access_token").asText();
            String refreshToken = tokenResponse.get("refresh_token").asText();
            int expiresIn = tokenResponse.get("expires_in").asInt();

            SessionContext.initialize(accessToken, refreshToken, expiresIn);

            System.out.println("[DEBUG] Access Token: " + accessToken);

            // Decodifica el JWT para extraer los claims (email, roles)
            String[] tokenParts = accessToken.split("\\.");
            if (tokenParts.length != 3) {
                System.out.println("[ERROR] Token malformado. No tiene 3 partes.");
                return null;
            }

            String payloadJson = new String(Base64.getUrlDecoder().decode(tokenParts[1]), StandardCharsets.UTF_8);
            System.out.println("[DEBUG] JWT Payload Decodificado: " + payloadJson);

            JsonNode payload = mapper.readTree(payloadJson);

            String username = payload.get("preferred_username").asText();
            System.out.println("[DEBUG] Username en token: " + username);

            JsonNode rolesNode = payload.path("resource_access").path("sistema-desktop").path("roles");

            String role = rolesNode.isArray() && rolesNode.size() > 0 ? rolesNode.get(0).asText().toUpperCase() : "UNKNOWN";
            System.out.println("[DEBUG] Rol extraído: " + role);

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            user.setId(0);

            System.out.println("[DEBUG] Usuario autenticado: " + user.getUsername() + ", Rol: " + user.getRole());

            return user;

        } catch (Exception e) {
            Logger.getLogger(UserRepositoryMS.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("[EXCEPTION] Error al procesar autenticación: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean saveUser(int id, String email, String password, String role) {
        // Ya no se maneja aquí el registro si estás usando Keycloak como IdP
        return false;
    }

    @Override
    public int getUserIdByEmail(String email) {
        // Podrías consultar otro microservicio si deseas, o dejar sin implementar
        return -1;
    }
}
