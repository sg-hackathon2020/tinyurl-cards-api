package com.rakole.tinyurl.config;

import com.google.common.collect.ImmutableSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Rhishikesh Akole", "https://github.com/sg-hackathon2020", "rhishikesh.akole@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Tiny URL and Cards API", "shorten the url using this API and generate cards", "0.1",
            "tos", DEFAULT_CONTACT,
            "", "", Collections.emptyList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(ImmutableSet.of("application/json"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}


/*jdbc:sqlserver://hackathondbserver.database.windows.net:1433;database=hackathonDB;user=hackathon@hackathondbserver;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;*/