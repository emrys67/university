package com.foxminded.university.controller;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.config.WebConfig;
import com.foxminded.university.controller.exception.ExceptionHandlerController;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Group;
import com.foxminded.university.services.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
@ExtendWith(MockitoExtension.class)
public class GroupsControllerTest {
    @Mock
    private GroupService groupService;
    @InjectMocks
    private GroupsController groupsController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(groupsController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void groupInfo() throws Exception {
        when(groupService.getGroupById(anyLong())).thenReturn(new Group());
        Group expected = groupService.getGroupById(1);
        mockMvc.perform(get("/groups/1")).andExpect(model().attribute("group", expected))
                .andExpect(view().name("groups/group-info"));
    }

    @Test
    void groupList() throws Exception {
        List expected = new ArrayList();
        when(groupService.getAllGroups()).thenReturn(expected);
        mockMvc.perform(get("/groups/list")).andExpect(model().attribute("groups", expected))
                .andExpect(view().name("groups/get-groups"));
    }

    @Test
    void groupInfoGetNotExistingGroup() throws Exception {
        when(groupService.getGroupById(anyLong())).thenThrow(new ServiceException());
        String expected = new ServiceException().getClass().getSimpleName();
        mockMvc.perform(get("/groups/1")).andExpect(model().attribute("exception", expected)).andExpect(view().name("errors/error"));
    }
}
