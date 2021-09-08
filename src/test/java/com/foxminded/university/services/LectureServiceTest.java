package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LectureServiceTest {
    @Test
    public void addLecture(){
    }

    @Test
    public void getLectureById(){

    }

    @Test
    public void deleteLectureById(){

    }

    @Test
    public void getAllLectures(){

    }

    @Test
    public void updateLecture(){

    }

    @Test
    public void addGroup(){

    }

    @Test
    public void getGroupsFromLecture(){

    }
}
