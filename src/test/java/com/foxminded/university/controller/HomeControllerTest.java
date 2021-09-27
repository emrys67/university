package com.foxminded.university.controller;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.config.WebConfig;
import com.foxminded.university.controller.exception.ExceptionHandlerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {
    @Autowired
    private HomeController homeController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void homeDisplayed() throws Exception {
        mockMvc.perform(get("/home")).andExpect(view().name("home/home"));
    }
}
