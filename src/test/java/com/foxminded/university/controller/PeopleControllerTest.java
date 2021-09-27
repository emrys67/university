package com.foxminded.university.controller;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.config.WebConfig;
import com.foxminded.university.controller.exception.ExceptionHandlerController;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Student;
import com.foxminded.university.entities.Teacher;
import com.foxminded.university.services.StudentService;
import com.foxminded.university.services.TeacherService;
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
public class PeopleControllerTest {
    @Mock
    private TeacherService teacherService;
    @Mock
    private StudentService studentService;
    @InjectMocks
    private PeopleController peopleController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(peopleController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    @Test
    void studentInfo() throws Exception {
        when(studentService.getStudentById(anyLong())).thenReturn(new Student());
        Student expected = studentService.getStudentById(1);
        mockMvc.perform(get("/people/student/1")).andExpect(model().attribute("student", expected))
                .andExpect(view().name("people/student-info"));
    }

    @Test
    void teacherInfo() throws Exception {
        when(teacherService.getTeacherById(anyLong())).thenReturn(new Teacher());
        Teacher expected = teacherService.getTeacherById(1);
        mockMvc.perform(get("/people/teacher/1")).andExpect(model().attribute("teacher", expected))
                .andExpect(view().name("people/teacher-info"));
    }

    @Test
    void teachersList() throws Exception {
        List expected = new ArrayList();
        when(teacherService.getAllTeachers()).thenReturn(expected);
        mockMvc.perform(get("/people/teachers")).andExpect(model().attribute("teachers", expected))
                .andExpect(view().name("people/get-teachers"));
    }

    @Test
    void studentsList() throws Exception {
        List expected = new ArrayList();
        when(studentService.getAllStudents()).thenReturn(expected);
        mockMvc.perform(get("/people/students")).andExpect(model().attribute("students", expected))
                .andExpect(view().name("people/get-students"));
    }

    @Test
    void studentInfoGetNotExistingStudent() throws Exception {
        when(studentService.getStudentById(anyLong())).thenThrow(new ServiceException());
        String expected = new ServiceException().getClass().getSimpleName();
        mockMvc.perform(get("/people/student/1")).andExpect(model().attribute("exception", expected))
                .andExpect(view().name("errors/error"));
    }

    @Test
    void teacherInfoGetNotExistingTeacher() throws Exception {
        when(teacherService.getTeacherById(anyLong())).thenThrow(new ServiceException());
        String expected = new ServiceException().getClass().getSimpleName();
        mockMvc.perform(get("/people/teacher/1")).andExpect(model().attribute("exception", expected))
                .andExpect(view().name("errors/error"));
    }
}
