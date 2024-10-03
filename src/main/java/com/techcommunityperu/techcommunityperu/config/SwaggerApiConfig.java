package com.techcommunityperu.techcommunityperu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerApiConfig {

    @Value("${techcommunityperu.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl(devUrl);
        server.setDescription("Tech Community Peru API");

        //Informacion de contacto
        Contact contact = new Contact();
        contact.setUrl("https://github.com/dsanchezchu");
        contact.setName("Diego Sanchez");
        contact.setEmail("diego2702015@outlook.com");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        //Info general de api
        Info info = new Info()
                .title("Tech Community Peru API")
                .version("1.0")
                .contact(contact)
                .license(mitLicense)
                .description("Tech Community Peru API");
        return new OpenAPI()
                .info(info)
                .addServersItem(server);
    }
}
