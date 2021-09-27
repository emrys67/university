package com.foxminded.university.controller;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.config.WebConfig;
import com.foxminded.university.controller.exception.ExceptionHandlerController;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Subject;
import com.foxminded.university.services.SubjectService;
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
public class SubjectControllerTest {
    @Mock
    private SubjectService subjectService;
    @InjectMocks
    private SubjectsController subjectsController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(subjectsController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void subjectInfo() throws Exception {
        when(subjectService.getSubjectById(anyLong())).thenReturn(new Subject());
        Subject expected = subjectService.getSubjectById(1);
        mockMvc.perform(get("/subjects/1")).andExpect(model().attribute("subject", expected))
                .andExpect(view().name("subjects/subject-info"));
    }

    @Test
    void subjectList() throws Exception {
        List expected = new ArrayList();
        when(subjectService.getAllSubjects()).thenReturn(expected);
        mockMvc.perform(get("/subjects/list")).andExpect(model().attribute("subjects", expected))
                .andExpect(view().name("subjects/get-subjects"));
    }

    @Test
    void groupInfoGetNotExistingSubject() throws Exception {
        when(subjectService.getSubjectById(anyLong())).thenThrow(new ServiceException());
        String expected = new ServiceException().getClass().getSimpleName();
        mockMvc.perform(get("/subjects/1")).andExpect(model().attribute("exception", expected)).andExpect(view().name("errors/error"));
    }
}
