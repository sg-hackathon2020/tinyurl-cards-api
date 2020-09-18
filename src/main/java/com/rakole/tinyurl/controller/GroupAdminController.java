package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.api.GroupAdminService;
import com.rakole.tinyurl.model.ToggleAdminRequestBody;
import com.rakole.tinyurl.model.UserAdminView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupAdminController {
    private final GroupAdminService groupAdminService;

    public GroupAdminController(GroupAdminService groupAdminService) {
        this.groupAdminService = groupAdminService;
    }

    @GetMapping("/api/v1/groups/{groupId}/admins")
    @CrossOrigin
    public ResponseEntity<List<UserAdminView>> getUserAdminView(@PathVariable int groupId) {
        //todo remove current logged in user from the list
        return new ResponseEntity(groupAdminService.getUserAdminView(groupId), HttpStatus.OK);
    }

    @PostMapping("/api/v1/groups/{groupId}/admins")
    @CrossOrigin
    public ResponseEntity<List<UserAdminView>> toggleAdmin(@RequestBody ToggleAdminRequestBody toggleAdminRequestBody) {
        System.out.println("i am here");
        Boolean addedFlag = groupAdminService.addRemoveAsAdmin(toggleAdminRequestBody.getGroupId(), toggleAdminRequestBody.getUserId());
        return new ResponseEntity(groupAdminService.getUserAdminView(toggleAdminRequestBody.getGroupId()), HttpStatus.OK);
    }
}
