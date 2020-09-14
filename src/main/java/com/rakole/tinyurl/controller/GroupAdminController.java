package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.api.GroupAdminService;
import com.rakole.tinyurl.model.UserAdminView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupAdminController {
    private final GroupAdminService groupAdminService;

    public GroupAdminController(GroupAdminService groupAdminService) {
        this.groupAdminService = groupAdminService;
    }

    @GetMapping("/api/v1/groups/{groupId}/admins")
    public ResponseEntity<List<UserAdminView>> getUserAdminView(@PathVariable int groupId) {
        //todo remove current logged in user from the list
        return new ResponseEntity<>(groupAdminService.getUserAdminView(1), HttpStatus.OK);
    }
}
