package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.GroupAdminService;
import com.rakole.tinyurl.api.GroupService;
import com.rakole.tinyurl.api.TUserService;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.model.TUser;
import com.rakole.tinyurl.model.UserAdminView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupAdminServiceImpl implements GroupAdminService {

    private final GroupService groupService;
    private final TUserService tUserService;

    public GroupAdminServiceImpl(GroupService groupService, TUserService tUserService) {
        this.groupService = groupService;
        this.tUserService = tUserService;
    }

    @Override
    //todo: add illegal argument exception handler on top controller
    public List<UserAdminView> getUserAdminView(int groupId) {
        Group group = groupService.findGroupById(groupId);

        Set<UserAdminView> currentAdmins = group.getAdmins().stream()
                .map(user -> UserAdminView.builder().id(user.getId())
                        .email(user.getEmail()).isAdmin(true).build())
                .collect(Collectors.toSet());

        List<TUser> allUsers = tUserService.getAllUsers();

        currentAdmins.addAll(allUsers.stream()
                .map(user -> UserAdminView.builder()
                        .isAdmin(false)
                        .email(user.getEmail())
                        .id(user.getId()).build()).collect(Collectors.toSet())

        );


        return new ArrayList<>(currentAdmins);
    }

    @Override
    public boolean addRemoveAsAdmin(int groupId, int userId) {
        return false;
    }
}
