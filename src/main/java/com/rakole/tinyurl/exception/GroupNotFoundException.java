package com.rakole.tinyurl.exception;

import lombok.Data;

@Data
public class GroupNotFoundException extends RuntimeException {
    private String message;
    private String details;
}
