package com.unicauca.CompanyService.dto;

import lombok.Data;

@Data
public class CompanyResponse {

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
}
