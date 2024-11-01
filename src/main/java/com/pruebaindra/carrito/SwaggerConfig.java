package com.pruebaindra.carrito;

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
                        .title("API de Carrito de Compras")
                        .version("1.0.0")
                        .description("Documentación de la API para el sistema de carrito de compras indra")
                        .termsOfService("https://www.example.com/terms")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Sebastian mendoza Carrasquilla")
                                .email("sebmenca3@gmail.com"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Licencia Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}
