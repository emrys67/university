package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Test
    public void addClassroom(){

    }

    @Test
    public void getStudentById(){

    }

    @Test
    public void deleteStudentById(){

    }

    @Test
    public void getAllStudents(){

    }

    @Test
    public void updateStudent(){

    }
}
