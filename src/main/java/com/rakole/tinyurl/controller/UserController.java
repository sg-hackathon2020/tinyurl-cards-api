package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.api.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final TUserService tUserService;

    @Autowired
    public UserController(TUserService tUserService) {
        this.tUserService = tUserService;
    }

    /***
     * this endpoint based on user in token checks if a user exists in system,
     * If the user does not exists, it creates a user in Tenant User (TUser) table*/
    @GetMapping("/api/v1/users/validate")
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> verifyOrCreateUser() {
        tUserService.verifyAndCreateUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
