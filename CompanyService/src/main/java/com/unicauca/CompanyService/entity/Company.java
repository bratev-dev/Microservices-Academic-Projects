package com.unicauca.CompanyService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {


    @Id
    private String id;

    private String name;
    private String email;
    private String sector;
    private String contactNames;
    private String contactLastNames;
    private String contactPhoneNumber;
    private String contactPosition;
    private String description;
    private String password;
    @Version
    private Long version;
}

