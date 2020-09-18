package com.rakole.tinyurl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = UrlNotFoundException.class)
    public ResponseEntity<Object> exception(UrlNotFoundException exception) {
        return new ResponseEntity<>("Either This url did not send 200 response" +
                " or this url does not exist in our DB", HttpStatus.NOT_FOUND);
    }
}
