package com.rakole.tinyurl.api;

import com.rakole.tinyurl.model.Group;

public interface GroupService {
    Group findGroupById(int groupId);
}
