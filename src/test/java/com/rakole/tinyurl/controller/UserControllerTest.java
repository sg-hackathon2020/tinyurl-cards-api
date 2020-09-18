package com.rakole.tinyurl.controller;

import com.rakole.tinyurl.model.Group;
import com.rakole.tinyurl.model.TUser;
import com.rakole.tinyurl.repository.GroupRepository;
import com.rakole.tinyurl.repository.TUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private TUserRepository tUserRepository;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    void verifyOrCreateUser() {
    }

    @Test
    void testMyJoinTable() throws Exception {
        given(groupRepository.findById(any())).willReturn(Optional.of(new Group()));
        given(tUserRepository.findById(any())).willReturn(Optional.of(new TUser()));
        mockMvc.perform(get("/testgroup")).andExpect(status().isOk())/*.
        andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))*/;
    }
}