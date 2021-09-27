package com.foxminded.university.controller;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.config.WebConfig;
import com.foxminded.university.controller.exception.ExceptionHandlerController;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.TimePeriod;
import com.foxminded.university.services.TimePeriodService;
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
public class TimePeriodControllerTest {
    @Mock
    private TimePeriodService timePeriodService;
    @InjectMocks
    private TimeperiodController timeperiodController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(timeperiodController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void timePeriodInfo() throws Exception {
        when(timePeriodService.getTimePeriodById(anyLong())).thenReturn(new TimePeriod());
        TimePeriod expected = timePeriodService.getTimePeriodById(1);
        mockMvc.perform(get("/timeperiod/1")).andExpect(model().attribute("timeperiod", expected))
                .andExpect(view().name("timeperiods/timeperiod-info"));
    }

    @Test
    void timePeriodInfoGetNotExistingtimePeriod() throws Exception {
        when(timePeriodService.getTimePeriodById(anyLong())).thenThrow(new ServiceException());
        String expected = new ServiceException().getClass().getSimpleName();
        mockMvc.perform(get("/timeperiod/1")).andExpect(model().attribute("exception", expected)).andExpect(view().name("errors/error"));
    }
}
