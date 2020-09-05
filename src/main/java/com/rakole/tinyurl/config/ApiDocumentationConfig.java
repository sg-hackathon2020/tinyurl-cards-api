package com.rakole.tinyurl.config;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "shorten the url using this API and generate cards",
                version = "V0.1",
                title = "Tiny URL and Cards API",
                contact = @Contact(
                        name = "Rhishikesh Akole",
                        email = "rhishikesh.akole@gmail.com",
                        url = "https://github.com/sg-hackathon2020"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
public interface ApiDocumentationConfig {
}
