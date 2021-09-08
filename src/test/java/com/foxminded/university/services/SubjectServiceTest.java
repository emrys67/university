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
public class SubjectServiceTest {
    @Test
    public void addSubject(){

    }

    @Test
    public void getSubjectById(){

    }

    @Test
    public void deleteSubjectById(){

    }

    @Test
    public void getAllSubjects(){

    }

    @Test
    public void updateSubject(){

    }

    @Test
    public void addTeacher(){

    }

    @Test
    public void getTeachersFromSubject(){

    }
}
