package com.unicauca.CompanyService.repository;

import com.unicauca.CompanyService.dto.UserRegisterRequest;
import com.unicauca.CompanyService.entity.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="auth-service",url= "http://localhost:8080/api/auth")
public interface AuthFeignClient {
    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody UserRegisterRequest registerRequest);
}
