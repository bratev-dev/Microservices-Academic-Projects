package com.unicauca.CompanyService.repository;

import com.unicauca.CompanyService.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    boolean existsByNIT(String NIT);
}
