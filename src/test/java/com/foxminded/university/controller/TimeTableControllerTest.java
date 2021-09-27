package com.foxminded.university.controller;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.config.WebConfig;
import com.foxminded.university.controller.exception.ExceptionHandlerController;
import com.foxminded.university.services.LectureService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
@ExtendWith(MockitoExtension.class)
public class TimeTableControllerTest {
    @Mock
    private LectureService lectureService;
    @InjectMocks
    private TimeTableController timeTableController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(timeTableController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void subjectList() throws Exception {
        List expected = new ArrayList();
        when(lectureService.getAllLectures()).thenReturn(expected);
        mockMvc.perform(get("/timetable/list")).andExpect(model().attribute("lectures", expected))
                .andExpect(view().name("timetable/get-timetable"));
    }
}
