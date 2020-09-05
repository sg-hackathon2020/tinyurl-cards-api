package com.rakole.tinyurl.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UrlNotFoundException extends RuntimeException {
    private String message;
    private String details;
}
