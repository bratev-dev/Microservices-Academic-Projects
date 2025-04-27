package com.unicauca.CompanyService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Company Service!";
    }
}
