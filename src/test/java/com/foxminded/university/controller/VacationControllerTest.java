package com.foxminded.university.controller;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.config.WebConfig;
import com.foxminded.university.controller.exception.ExceptionHandlerController;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Vacation;
import com.foxminded.university.services.VacationService;
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
public class VacationControllerTest {
    @Mock
    private VacationService vacationService;
    @InjectMocks
    private VacationController vacationController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(vacationController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void vacationInfo() throws Exception {
        when(vacationService.getVacationById(anyLong())).thenReturn(new Vacation());
        Vacation expected = vacationService.getVacationById(1);
        mockMvc.perform(get("/vacation/1")).andExpect(model().attribute("vacation", expected))
                .andExpect(view().name("vacations/vacation-info"));
    }

    @Test
    void vacationInfoGetNotExistingVacation() throws Exception {
        when(vacationService.getVacationById(anyLong())).thenThrow(new ServiceException());
        String expected = new ServiceException().getClass().getSimpleName();
        mockMvc.perform(get("/vacation/1")).andExpect(model().attribute("exception", expected))
                .andExpect(view().name("errors/error"));
    }
}
