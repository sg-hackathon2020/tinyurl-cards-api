package com.rakole.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableConfigurationProperties
public class TinyurlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinyurlApplication.class, args);
    }

}
