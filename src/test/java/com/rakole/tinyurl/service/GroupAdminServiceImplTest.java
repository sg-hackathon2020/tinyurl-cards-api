package com.rakole.tinyurl.service;

import com.rakole.tinyurl.api.GroupService;
import com.rakole.tinyurl.api.TUserService;
import com.rakole.tinyurl.model.Group;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GroupAdminServiceImplTest {

    @Mock
    GroupService groupService;

    @Mock
    TUserService tUserService;

    @InjectMocks
    GroupAdminServiceImpl groupAdminService;

    /*@Test
    void getUserAdminView() {
        Group group = Group.builder().build();
        //given
        given(groupService.findGroupById(1)).willReturn()
        //when

        //then
    }*/

    @Test
    void addRemoveAsAdmin() {
    }


}