package com.unicauca.CompanyService.controller;

import com.unicauca.CompanyService.entity.Company;
import com.unicauca.CompanyService.repository.CompanyRepository;
import com.unicauca.CompanyService.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")

public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/test")
    //@PreAuthorize("permitAll()")
    public String test() {
        System.out.println("=== ENDPOINT TEST FUNCIONANDO ===");
        return "Funcionando!";
    }

    // Crear una nueva empresa
    @PostMapping
    //@PreAuthorize("permitAll()")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        System.out.println("=== PETICIÓN LLEGÓ AL CONTROLLER ===");
        System.out.println("NIT: " + company.getNIT());
        System.out.println("Nombre: " + company.getName());

        try {
            Company createdCompany = companyService.createCompany(company);
            System.out.println("Usuario creado exitosamente");
            return ResponseEntity.created(URI.create("/api/companies/" + createdCompany.getNIT())).body(createdCompany);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Autowired
    private CompanyService empresaService;

    @GetMapping("/buscar")
   // @PreAuthorize("hasRole('company')")
    public ResponseEntity<Boolean> existeEmpresaPorIdYEmail(
            @RequestParam String NIT) {

        boolean existe = empresaService.existsCompany(NIT);
        return ResponseEntity.ok(existe);
    }

    // Obtener todas las empresas
    @GetMapping
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
    @Autowired
    private CompanyRepository companyRepository;
    // Obtener una empresa por su ID
   // @GetMapping("/{id}")

//    public ResponseEntity<Company> getCompanyById(@PathVariable String id) {
//        return companyService.getCompanyById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/nombre/{id}")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public ResponseEntity<String> getNombreEmpresaById(@PathVariable String NIT) {
        Optional<Company> company = companyService.getCompanyById(NIT);
        if (company.isPresent()) {
            return ResponseEntity.ok(company.get().getName()); // Solo retorna el nombre
        } else {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('company') or hasRole('coordinator')")
    public ResponseEntity<Company> getCompanyById(@PathVariable String NIT) {
        Optional<Company> company = companyService.getCompanyById(NIT);
        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        } else {
            return ResponseEntity.notFound().build();  // Devolver 404 si no se encuentra la empresa
        }
    }

    // Actualizar una empresa
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('company')")
    public ResponseEntity<Company> updateCompany(@PathVariable String NIT, @RequestBody Company companyDetails) {
        Optional<Company> existingCompany = companyService.getCompanyById(NIT);

        if (existingCompany.isPresent()) {
            Company updatedCompany = companyService.updateCompany(NIT, companyDetails);
            return ResponseEntity.ok(updatedCompany);
        } else {
            return ResponseEntity.notFound().build();  // Devolver 404 si no se encuentra la empresa
        }
    }

    // Eliminar una empresa
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('company')")
    public ResponseEntity<Void> deleteCompany(@PathVariable String NIT) {
        Optional<Company> existingCompany = companyService.getCompanyById(NIT);
        if (existingCompany.isPresent()) {
            companyService.deleteCompany(NIT);
            return ResponseEntity.noContent().build();  // Devolver 204 No Content si la eliminación fue exitosa
        } else {
            return ResponseEntity.notFound().build();  // Devolver 404 si no se encuentra la empresa
        }
    }
}
