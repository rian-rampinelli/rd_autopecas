package com.rd.autopecas.erp_autopecas.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RD-Autopeças")
                        .version("1.0")
                        .description("API REST para gerenciamento uma loja de autopeças")
                        .contact(new Contact()
                                .name("Rian")
                                .email("riaann.barbosa@gmail.com"))
                        .license(new License()
                                .name("MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio do projeto")
                        .url("https://github.com/rian-rampinelli/rd_autopecas"));
    }
}
