package com.mycompany.gestionproyectosacademicos.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.gestionproyectosacademicos.access.CompanyRepositoryMS;
import com.mycompany.gestionproyectosacademicos.filter.IFilter;
import com.mycompany.gestionproyectosacademicos.access.Factory;
import com.mycompany.gestionproyectosacademicos.access.ICompanyRepository;
import com.mycompany.gestionproyectosacademicos.access.ICoordinatorRepository;
import com.mycompany.gestionproyectosacademicos.entities.User;
import com.mycompany.gestionproyectosacademicos.access.IUserRepository;
import com.mycompany.gestionproyectosacademicos.access.ProjectRepositoryMS;
import com.mycompany.gestionproyectosacademicos.infra.Messages;
import com.mycompany.gestionproyectosacademicos.presentation.GUICompany;
import com.mycompany.gestionproyectosacademicos.presentation.GUICoordinator;
import com.mycompany.gestionproyectosacademicos.presentation.GUIStudentProjectList;
import java.net.http.HttpClient;
//import com.mycompany.gestionproyectosacademicos.presentacion.GUIStudent;
import javax.swing.JFrame;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.nio.charset.StandardCharsets;


/**
 * Servicio de autenticación
 */
public class AuthService {

    private User authenticatedUser;
    private final IUserRepository userRepository;
    //private ICompanyRepository companyRepository;
    //private final IProjectRepository projectRepository = Factory.getInstance().getRepository(IProjectRepository.class, "ARRAYS");

    public AuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JFrame login(String email, String password) {
        User user = userRepository.authenticate(email, password);

        if (user == null) {
            return null;
        }
        return getGUIForRole(user);
    }

    private JFrame getGUIForRole(User user) {
        switch (user.getRole()) {
            case "STUDENT":
                GUIStudentProjectList student = new GUIStudentProjectList();
                student.setExtendedState(JFrame.MAXIMIZED_BOTH);
                return student;
            case "COMPANY":
                CompanyRepositoryMS companyRepo = new CompanyRepositoryMS();
                ProjectRepositoryMS projectRepo = new ProjectRepositoryMS();

                if (companyRepo == null) {
                    Messages.showMessageDialog("❌ Error: No se encontró el repositorio de Company en Factory", "Error");
                    return null;
                }
                CompanyService companyService = new CompanyService(companyRepo);
                ProjectService projectService = new ProjectService(projectRepo);
                GUICompany guiCompany = new GUICompany(companyService, projectService, user.getEmail());

                guiCompany.setExtendedState(JFrame.MAXIMIZED_BOTH);
                return guiCompany;

            case "COORDINATOR":
                GUICoordinator instance = new GUICoordinator();
                instance.setExtendedState(JFrame.MAXIMIZED_BOTH);
                return instance;
            default:
                return null;
        }

    }

    public static boolean refreshAccessToken() {
        try {
            String body = "grant_type=refresh_token"
                    + "&client_id=sistema-desktop"
                    + "&refresh_token=" + SessionContext.getRefreshToken();

            HttpPost request = new HttpPost("http://localhost:8080/realms/sistema/protocol/openid-connect/token");
            request.setEntity(new StringEntity(body));
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");

            org.apache.http.client.HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return false;
            }

            String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode tokenResponse = mapper.readTree(json);

            String newAccess = tokenResponse.get("access_token").asText();
            String newRefresh = tokenResponse.get("refresh_token").asText();
            int expiresIn = tokenResponse.get("expires_in").asInt();

            SessionContext.updateAccessToken(newAccess, newRefresh, expiresIn);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
