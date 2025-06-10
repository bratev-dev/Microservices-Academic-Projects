package com.mycompany.gestionproyectosacademicos.entities;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
    @JsonProperty("NIT")
    private String NIT;
    
    private String name;
    private String email;
    private String sector;
    private String contactNames;
    private String contactLastNames;
    private String contactPhoneNumber;
    private String contactPosition;
    private String password;
    
    public Company(String NIT, String name, String email, String sector, String contactNames,
            String contactLastNames, String contactPhoneNumber, String contactPosition, String password) {
        this.NIT = NIT;
        this.name = name;
        this.email = email;
        this.sector = sector;
        this.contactNames = contactNames;
        this.contactLastNames = contactLastNames;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactPosition = contactPosition;
        this.password = password;
    }
    
    public Company() {
    }
    
    // CAMBIO AQUÍ: Agregar @JsonProperty al getter también
    @JsonProperty("NIT")
    public String getNIT() {
        return NIT;
    }
    
    @JsonProperty("NIT")
    public void setNIT(String NIT) {
        this.NIT = NIT;
    }
    
    // Resto de getters y setters sin cambios...
    public String getName() {
        return name;
    }
    
    public String getSector() {
        return sector;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}