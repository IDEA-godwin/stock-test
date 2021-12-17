package com.example.stocks.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private final String SECURITY_SCHEME_NAME = "auth key";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addSecurityItem(securityRequirement())
            .components(components())
            .info(info());
    }

    private Info info() {
        return new Info()
            .title("App documentation")
            .description("Information about the supported endpoint")
            .version("0.1")
            .contact(new Contact().email("godwinaquinas@email.com").name("godwin"));
    }

    private SecurityRequirement securityRequirement() {
        return new SecurityRequirement().addList(SECURITY_SCHEME_NAME);
    }

    private Components components() {
        return new Components()
            .addSecuritySchemes(SECURITY_SCHEME_NAME, securityScheme());
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
            .name(SECURITY_SCHEME_NAME)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT");
    }
}

