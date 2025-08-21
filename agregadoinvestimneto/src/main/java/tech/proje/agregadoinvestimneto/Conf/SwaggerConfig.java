package tech.proje.agregadoinvestimneto.Conf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Minha API de Exemplo")
                        .version("2.0")
                        .description("Documentação detalhada da API")
                        .contact(new Contact()
                                .name("Alan Silva")
                                .email("alan@email.com")
                                .url("https://meusite.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
        );
    }
}
