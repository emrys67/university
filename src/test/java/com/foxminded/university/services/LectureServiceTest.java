package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.*;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Lecture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
public class LectureServiceTest {
    @Mock
    private LectureJdbcDao lectureJdbcDao;
    @InjectMocks
    private LectureService lectureService;
    @Mock
    Group group;
    @Mock
    Lecture lecture;

    @Test
    public void addLectureDaoWasUsed() {
        lectureService.addLecture(any());
        verify(lectureJdbcDao, times(1)).create(any());
    }

    @Test
    public void getLectureByIdDaoWasUsed() {
        lectureService.getLectureById((long) 1);
        verify(lectureJdbcDao, times(1)).getById(any());
    }

    @Test
    public void deleteLectureByIdDaoWasUsed() {
        lectureService.deleteLectureById((long) 1);
        verify(lectureJdbcDao, times(1)).delete(any());
    }

    @Test
    public void getAllLecturesDaoWasUsed() {
        lectureService.getAllLectures();
        verify(lectureJdbcDao, times(1)).getAll();
    }

    @Test
    public void updateLectureDaoWasUsed() {
        lectureService.updateLecture(any());
        verify(lectureJdbcDao, times(1)).update(any());
    }

    @Test
    public void addGroupDaoWasUsed() {
        when(group.getId()).thenReturn((long) 1);
        when(lecture.getId()).thenReturn((long) 1);
        lectureService.addGroup(lecture, group);
        verify(lectureJdbcDao, times(1)).addGroup((long) 1, (long) 1);
    }

    @Test
    public void getGroupsFromLectureDaoWasUsed() {
        lectureService.getGroupsFromLecture((long) 1);
        verify(lectureJdbcDao, times(1)).getGroupsFromLecture(anyLong());
    }
}
