package com.personalrchabane.videogame_library_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Video Game Library API")
                        .description("API pour gérer une bibliothèque de jeux vidéo")
                        .version("1.0.0"));
    }
}
