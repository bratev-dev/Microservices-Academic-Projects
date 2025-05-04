package com.unicauca.CompanyService.dto;

import lombok.Data;

@Data
public class CompanyResponse {
    private long id;

    private String name;
    private String email;
    private String sector;
    private String contactNames;
    private String contactLastNames;
    private String contactPhoneNumber;
    private String contactPosition;
    private String password;

}
