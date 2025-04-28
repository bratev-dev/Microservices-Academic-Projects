package com.unicauca.CompanyService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPublishedEvent {

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
