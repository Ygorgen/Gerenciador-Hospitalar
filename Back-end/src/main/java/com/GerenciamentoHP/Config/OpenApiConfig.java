package com.GerenciamentoHP.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's FROM 0 WITH JAVA , SPRING BOOT , KUBERNETS AND DORCKER")
                        .version("v1")
                        .description("REST API's FROM 0 WITH JAVA , SPRING BOOT , KUBERNETS AND DORCKER")
                        .license(new License()
                                .name("Apache 2.0")
                        )
                )
                ;
    }
}
