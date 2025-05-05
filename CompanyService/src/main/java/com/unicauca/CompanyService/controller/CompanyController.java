package com.unicauca.CompanyService.controller;

import com.unicauca.CompanyService.entity.Company;
import com.unicauca.CompanyService.repository.CompanyRepository;
import com.unicauca.CompanyService.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Crear una nueva empresa
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        System.out.println("Recibido ID: " + company.getId());
        return ResponseEntity.created(URI.create("/api/companies/" + createdCompany.getId())).body(createdCompany);
    }

    @Autowired
    private CompanyService empresaService;

    @GetMapping("/buscar")
    public ResponseEntity<Boolean> existeEmpresaPorIdYEmail(
            @RequestParam Long id,
            @RequestParam String email) {

        boolean existe = empresaService.existsCompany(id, email);
        return ResponseEntity.ok(existe);
    }

    // Obtener todas las empresas
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
    @Autowired
    private CompanyRepository companyRepository;
    // Obtener una empresa por su ID
    @GetMapping("/{id}")

//    public ResponseEntity<Company> getCompanyById(@PathVariable String id) {
//        return companyService.getCompanyById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    public ResponseEntity<Company> getCompanyById(@PathVariable long id) {
        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        } else {
            return ResponseEntity.notFound().build();  // Devolver 404 si no se encuentra la empresa
        }
    }

    // Actualizar una empresa
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable long id, @RequestBody Company companyDetails) {
        Optional<Company> existingCompany = companyService.getCompanyById(id);

        if (existingCompany.isPresent()) {
            Company updatedCompany = companyService.updateCompany(id, companyDetails);
            return ResponseEntity.ok(updatedCompany);
        } else {
            return ResponseEntity.notFound().build();  // Devolver 404 si no se encuentra la empresa
        }
    }

    // Eliminar una empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable long id) {
        Optional<Company> existingCompany = companyService.getCompanyById(id);
        if (existingCompany.isPresent()) {
            companyService.deleteCompany(id);
            return ResponseEntity.noContent().build();  // Devolver 204 No Content si la eliminación fue exitosa
        } else {
            return ResponseEntity.notFound().build();  // Devolver 404 si no se encuentra la empresa
        }
    }
}
