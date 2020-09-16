package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.GroupService;
import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.repository.GroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    GroupRepository groupRepository;
    @InjectMocks
    GroupServiceImpl groupService;

    @Test
    @DisplayName("test if findGroupById gets called only once")
    void findGroupById() {
        //given
        Group group = Group.builder().id(1).build();
        given(groupRepository.findById(1)).willReturn(java.util.Optional.ofNullable(group));

        //when
        Group returnedGroup = groupService.findGroupById(1);

        //then
        then(groupRepository).should(times(1)).findById(1);
    }
}