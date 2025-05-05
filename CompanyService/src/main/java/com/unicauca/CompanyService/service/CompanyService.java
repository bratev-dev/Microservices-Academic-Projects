package com.unicauca.CompanyService.service;

import com.unicauca.CompanyService.dto.UserRegisterRequest;
import com.unicauca.CompanyService.entity.Company;
import com.unicauca.CompanyService.repository.AuthFeignClient;
import com.unicauca.CompanyService.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;
    private final AuthFeignClient authFeignClient;

    @Transactional
    // Crear una nueva empresa
    public Company createCompany(Company company) {

        Company saveCompany = companyRepository.save(company);
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder()
                .email(company.getEmail())
                .password(company.getPassword())
                .role("COMPANY")
                .build();
        authFeignClient.register(userRegisterRequest);

        return saveCompany;

    }

    // Obtener todas las empresas
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Obtener una empresa por ID
    public Optional<Company> getCompanyById(long id) {
        return companyRepository.findById(id);
    }


    // Actualizar una empresa
    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con id " + id));

        company.setName(companyDetails.getName());
        company.setEmail(companyDetails.getEmail());

        return companyRepository.save(company);
    }

    // Eliminar una empresa
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con id " + id));

        companyRepository.delete(company);
    }
}
