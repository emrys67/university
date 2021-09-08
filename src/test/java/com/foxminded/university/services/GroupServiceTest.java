package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
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
public class GroupServiceTest {
    @Test
    public void addGroup(){

    }

    @Test
    public void getGroupById(){

    }

    @Test
    public void deleteGroupById(){

    }

    @Test
    public void getAllGroups(){

    }

    @Test
    public void updateGroup(){

    }

    @Test
    public void addStudent(){

    }

    @Test
    public void getStudentsFromGroup(){

    }
}
