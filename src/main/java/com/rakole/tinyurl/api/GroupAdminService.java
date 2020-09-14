package com.rakole.tinyurl.api;

import com.rakole.tinyurl.model.UserAdminView;

import java.util.List;

public interface GroupAdminService {

    List<UserAdminView> getUserAdminView(int groupId);

    boolean addRemoveAsAdmin(int groupId, int userId);
}
