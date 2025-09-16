package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservices REST API Documentation",
                description = "Eazybank Accounts microservice REST API Document",
                version = "v1",
                contact = @Contact(
                        name = "Ngo Quang Truong",
                        email = "ngotruong764@gmail.com",
                        url = "https://www.google.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.google.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EasyBank Accounts microservice REST API Documentation",
                url = "https://www.easybytes.com/swagger-ui.html"
        )
)
@EnableConfigurationProperties({AccountsContactInfoDto.class})
@EnableFeignClients
@EnableDiscoveryClient
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
