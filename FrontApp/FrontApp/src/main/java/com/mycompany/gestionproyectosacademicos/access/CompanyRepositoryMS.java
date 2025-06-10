/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionproyectosacademicos.access;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.mycompany.gestionproyectosacademicos.entities.Company;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CompanyRepositoryMS implements ICompanyRepository {

    private static final String apiUrl = "http://localhost:8083/company/api/companies";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean save(Company company) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            // CAMBIO 1: Asegurar UTF-8 en Content-Type
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // CAMBIO 2: Agregar Accept header con UTF-8
            con.setRequestProperty("Accept", "application/json; charset=UTF-8");

            con.setDoOutput(true);

            String json = mapper.writeValueAsString(company);
            System.out.println("📦 JSON enviado: " + json);

            // CAMBIO 3: Asegurar UTF-8 al escribir
            try (OutputStream os = con.getOutputStream(); OutputStreamWriter writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
                writer.write(json);
                writer.flush();
            }

            int code = con.getResponseCode();
            System.out.println("🔁 Código HTTP: " + code);

            // CAMBIO 4: Asegurar UTF-8 al leer la respuesta
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream(),
                    StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("📨 Respuesta del servidor: " + response.toString());
            }

            con.disconnect();

            if (code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED) {
                JOptionPane.showMessageDialog(null, "✅ Empresa registrada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "❌ Error al registrar empresa. Código HTTP: " + code, "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Excepción al llamar al servicio: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsCompany(String NIT, String email) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        boolean exists = false;

        try {
            // Codificar el parámetro email por si tiene caracteres especiales
            String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8.toString());

            // Construir la URL con los parámetros
            String apiUrl = "http://localhost:8083/company/api/companies/buscar?nit=" + NIT + "&email=" + encodedEmail;

            // Crear solicitud GET
            HttpGet request = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                exists = Boolean.parseBoolean(jsonResponse); // Interpretar la respuesta JSON "true"/"false"
            } else {
                Logger.getLogger(getClass().getName()).log(Level.WARNING,
                        "Fallo al verificar existencia de empresa. Código: " + statusCode);
            }
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en existsCompany", e);
        }

        return exists;
    }

    @Override
    public Company findByNIT(String idCompany) {
        // Construir la URL completa para obtener la compañía
        String url = apiUrl + idCompany;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.setHeader("Accept", "application/json");

            // Ejecutar la solicitud GET
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            // Comprobar si la respuesta es exitosa (código 200)
            if (statusCode == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();

                // Configurar ObjectMapper para manejar posibles problemas
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                // Mapear la respuesta JSON a un objeto Company
                return mapper.readValue(jsonResponse, Company.class);
            } else if (statusCode == 404) {
                // La compañía no fue encontrada
                Logger.getLogger(CompanyRepositoryMS.class.getName())
                        .log(Level.INFO, "No se encontró la compañía con ID: {0}", idCompany);
                return null;
            } else {
                // Otros errores
                String errorResponse = EntityUtils.toString(response.getEntity());
                Logger.getLogger(CompanyRepositoryMS.class.getName())
                        .log(Level.SEVERE, "Error al obtener la compañía. Código: {0}, Respuesta: {1}",
                                new Object[]{statusCode, errorResponse});
            }
        } catch (IOException ex) {
            Logger.getLogger(CompanyRepositoryMS.class.getName())
                    .log(Level.SEVERE, "Error en la conexión al buscar compañía con ID: " + idCompany, ex);
        }
        return null; // Retorna null si ocurre un error
    }

}
