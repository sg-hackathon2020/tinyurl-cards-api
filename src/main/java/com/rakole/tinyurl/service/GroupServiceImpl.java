package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.GroupService;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Group findGroupById(int groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(IllegalArgumentException::new);
    }

}
