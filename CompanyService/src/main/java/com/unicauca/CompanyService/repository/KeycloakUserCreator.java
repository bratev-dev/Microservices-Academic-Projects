package com.unicauca.CompanyService.repository;

import com.unicauca.CompanyService.dto.UserRepresentation;
import com.unicauca.CompanyService.entity.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserCreator {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String KEYCLOAK_BASE_URL = "http://localhost:8080";
    // Para autenticación del admin
    private final String AUTH_REALM = "master";
    // Para operaciones en el realm objetivo
    private final String TARGET_REALM = "sistema";

    private final String CLIENT_ID = "admin-cli";
    private final String ADMIN_USER = "admin"; // admin de Keycloak
    private final String ADMIN_PASS = "admin"; // password del admin de Keycloak
    //private final String CLIENT_SECRET = "Hs9tPitAPOuX1oorA0bewzo0hUxxbpcX"; // Agregar esta constante

    public void createUserInKeycloak(Company company) {
        String token = getAdminAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        Map<String, Object> userPayload = new HashMap<>();
        userPayload.put("enabled", true);
        userPayload.put("username", company.getNIT()); // puedes usar el NIT como username
        userPayload.put("email", company.getEmail());

        userPayload.put("firstName", company.getName()); // Nombre de la empresa como nombre
        userPayload.put("lastName", "Empresa"); // puedes guardar "Empresa" o algo fijo

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", company.getPassword());
        credentials.put("temporary", false);

        userPayload.put("credentials", List.of(credentials));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userPayload, headers);

        String createUserUrl = KEYCLOAK_BASE_URL + "/admin/realms/" + TARGET_REALM + "/users";

        ResponseEntity<String> response = restTemplate.postForEntity(createUserUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            log.info("Usuario creado en Keycloak");

            // Asignar rol "company"
            assignRoleToUser(token, company.getNIT(), "company");

        } else {
            throw new RuntimeException("Error al crear usuario en Keycloak: " + response.getStatusCode());
        }
    }
    private void assignRoleToUser(String token, String username, String roleName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        // Buscar el ID del usuario
        String userSearchUrl = KEYCLOAK_BASE_URL + "/admin/realms/" + TARGET_REALM + "/users?username=" + username;
        ResponseEntity<UserRepresentation[]> response = restTemplate.exchange(
                userSearchUrl,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                UserRepresentation[].class
        );

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null || response.getBody().length == 0) {
            throw new RuntimeException("No se encontró el usuario en Keycloak");
        }

        String userId = response.getBody()[0].getId();

        // Obtener ID del cliente sistema-desktop
        String clientsUrl = KEYCLOAK_BASE_URL + "/admin/realms/" + TARGET_REALM + "/clients?clientId=sistema-desktop";
        ResponseEntity<List> clientsResponse = restTemplate.exchange(
                clientsUrl,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                List.class
        );

        if (clientsResponse.getStatusCode() != HttpStatus.OK || clientsResponse.getBody() == null || clientsResponse.getBody().isEmpty()) {
            throw new RuntimeException("No se encontró el cliente sistema-desktop");
        }

        Map<String, Object> client = (Map<String, Object>) clientsResponse.getBody().get(0);
        String clientId = (String) client.get("id");

        // Obtener el rol dentro del cliente
        String roleUrl = KEYCLOAK_BASE_URL + "/admin/realms/" + TARGET_REALM + "/clients/" + clientId + "/roles/" + roleName;
        ResponseEntity<Map> roleResponse = restTemplate.exchange(
                roleUrl,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Map.class
        );

        if (roleResponse.getStatusCode() != HttpStatus.OK || roleResponse.getBody() == null) {
            throw new RuntimeException("No se encontró el rol en el cliente sistema-desktop");
        }

        Map<String, Object> role = roleResponse.getBody();

        // Asignar el rol como client role mapping
        List<Map<String, Object>> roles = Collections.singletonList(role);
        String assignUrl = KEYCLOAK_BASE_URL + "/admin/realms/" + TARGET_REALM + "/users/" + userId + "/role-mappings/clients/" + clientId;

        ResponseEntity<Void> assignResponse = restTemplate.exchange(
                assignUrl,
                HttpMethod.POST,
                new HttpEntity<>(roles, headers),
                Void.class
        );

        if (!assignResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("No se pudo asignar el rol de cliente al usuario");
        }

        log.info("Rol '{}' asignado correctamente al usuario '{}' en cliente '{}'", roleName, username, clientId);
    }



    private String getAdminAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", CLIENT_ID);
        //form.add("client_secret", CLIENT_SECRET); // Agregar esta línea
        form.add("username", ADMIN_USER);
        form.add("password", ADMIN_PASS);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        String tokenUrl = KEYCLOAK_BASE_URL + "/realms/" + AUTH_REALM + "/protocol/openid-connect/token";

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return (String) response.getBody().get("access_token");
        } else {
            throw new RuntimeException("No se pudo obtener el token de Keycloak");
        }
    }
}
