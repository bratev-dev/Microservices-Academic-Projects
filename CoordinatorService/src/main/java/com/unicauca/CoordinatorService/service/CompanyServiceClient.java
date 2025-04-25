package com.unicauca.CoordinatorService.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "company-service", url = "http://localhost:8081/")
public interface CompanyServiceClient {

}
