package com.rakole.tinyurl.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("tiny.custom.encoding")
@Component
@Data
public class CustomEncoderProperties {
    private int length = 6;
}
