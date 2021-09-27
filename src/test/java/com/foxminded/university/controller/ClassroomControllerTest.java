package com.foxminded.university.controller;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.config.WebConfig;
import com.foxminded.university.controller.exception.ExceptionHandlerController;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Classroom;
import com.foxminded.university.services.ClassroomService;
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
public class ClassroomControllerTest {
    @Mock
    private ClassroomService classroomService;
    @InjectMocks
    private ClassroomController classroomController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void classroomInfo() throws Exception {
        when(classroomService.getClassroomById(anyLong())).thenReturn(new Classroom());
        Classroom expected = classroomService.getClassroomById(1);
        mockMvc.perform(get("/classroom/1")).andExpect(model().attribute("classroom", expected))
                .andExpect(view().name("classrooms/classroom-info"));
    }

    @Test
    void classroomInfoGetNotExistingClassroom() throws Exception {
        when(classroomService.getClassroomById(anyLong())).thenThrow(new ServiceException());
        String expected = new ServiceException().getClass().getSimpleName();
        mockMvc.perform(get("/classroom/1")).andExpect(model().attribute("exception", expected)).andExpect(view().name("errors/error"));
    }
}
