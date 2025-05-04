package com.unicauca.CompanyService.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Version;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {


    @Id
    @JsonProperty("nit")
    private Long id;

    private String name;
    private String email;
    private String sector;
    private String contactNames;
    private String contactLastNames;
    private String contactPhoneNumber;
    private String contactPosition;
    private String password;


    @Version
    private Long version;
}

