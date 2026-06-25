package com.example.syntax_error_studios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
            .info(new Info()
                        .title("API Venta de Videojuegos")
                        .version("1.0")
                        .description("Documentacion de la API Para el sistema de venta de videojuegos"));   

    }

}
