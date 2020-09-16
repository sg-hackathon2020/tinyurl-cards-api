package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.api.GroupAdminService;
import com.rakole.tinyurl.api.TUserService;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.model.TUser;
import com.rakole.tinyurl.model.UserAdminView;
import com.rakole.tinyurl.repository.GroupRepository;
import com.rakole.tinyurl.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final TUserService tUserService;
    private final GroupRepository groupRepository;
    private final TUserRepository tUserRepository;
    private final GroupAdminService groupAdminService;

    @Autowired
    public UserController(TUserService tUserService, GroupRepository groupRepository, TUserRepository tUserRepository, GroupAdminService groupAdminService) {
        this.tUserService = tUserService;
        this.groupRepository = groupRepository;
        this.tUserRepository = tUserRepository;
        this.groupAdminService = groupAdminService;
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

    @GetMapping("testgroup")
    public void testMyJoinTable() {
        Group group = groupRepository.findById(1).get();
        TUser user = tUserRepository.findById(1).get();

        user.getGroups().add(group);
        tUserRepository.save(user);
    }

    @GetMapping("testView")
    public void test2() {
        List<UserAdminView> usd = groupAdminService.getUserAdminView(1);
        usd.forEach(System.out::println);
    }

}
