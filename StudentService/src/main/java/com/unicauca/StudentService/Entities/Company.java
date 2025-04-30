package com.unicauca.StudentService.Entities;

/**
 *
 * @author jpala
 */
public class Company {
    private String name;
    private String nit;
    private String email;
    private String sector;
    private String contactNames;
    private String contactLastNames;
    private String contactPhoneNumber;
    private String contactPosition;
    
    public Company(){}//Constructor vacio para Jackson. Importante

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getContactNames() {
        return contactNames;
    }

    public void setContactNames(String contactNames) {
        this.contactNames = contactNames;
    }

    public String getContactLastNames() {
        return contactLastNames;
    }

    public void setContactLastNames(String contactLastNames) {
        this.contactLastNames = contactLastNames;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }
    
    
    
}
