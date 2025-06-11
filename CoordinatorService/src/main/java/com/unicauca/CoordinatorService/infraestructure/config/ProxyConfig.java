package com.unicauca.CoordinatorService.infraestructure.config;

import com.unicauca.CoordinatorService.adapter.out.persistence.FeignCompanyRepositoryClient;
import com.unicauca.CoordinatorService.adapter.out.proxy.CompanyRepositoryProxy;
import com.unicauca.CoordinatorService.application.port.out.CompanyRepositoryPort;
import com.unicauca.CoordinatorService.application.port.out.CompanyServiceClient;
import com.unicauca.CoordinatorService.infraestructure.persistence.repository.JpaProjectSpringDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {

    @Bean
    public FeignCompanyRepositoryClient feignCompanyRepositoryClient(
            CompanyServiceClient companyServiceClient,
            JpaProjectSpringDataRepository projectRepository
    ) {
        return new FeignCompanyRepositoryClient(companyServiceClient, projectRepository);
    }


    @Bean(name = "companyRepositoryPort")
    public CompanyRepositoryPort companyRepositoryPort(FeignCompanyRepositoryClient feignImpl) {
        return new CompanyRepositoryProxy(feignImpl);
    }


}
