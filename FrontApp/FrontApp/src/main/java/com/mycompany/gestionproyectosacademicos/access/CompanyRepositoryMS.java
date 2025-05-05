/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionproyectosacademicos.access;

import com.mycompany.gestionproyectosacademicos.entities.Company;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyRepositoryMS implements ICompanyRepository {

    private static final String apiUrl = "http://localhost:8081/api/companies/";

    @Override
    public boolean save(Company newCompany) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existsCompany(Long nit, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Company findByNIT(Long idCompany) {
        // Construir la URL completa para obtener la compañía
        String url = apiUrl + idCompany;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            // Ejecutar la solicitud GET
            HttpResponse response = httpClient.execute(request);

            // Comprobar si la respuesta es exitosa (código 200)
            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                // Mapear la respuesta JSON a un objeto Company
                return mapper.readValue(jsonResponse, Company.class);
            } else {
                Logger.getLogger(CompanyRepositoryMS.class.getName())
                        .log(Level.SEVERE, "Error al obtener la compañía. Código: {0}", response.getStatusLine().getStatusCode());
            }
        } catch (IOException ex) {
            Logger.getLogger(CompanyRepositoryMS.class.getName()).log(Level.SEVERE, "Error en la conexión", ex);
        }

        return null; // Retorna null si ocurre un error
    }

}
