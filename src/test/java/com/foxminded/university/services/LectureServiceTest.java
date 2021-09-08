package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.*;
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
    @Mock
    private SubjectJdbcDao subjectJdbcDao;
    @Mock
    private TeacherJdbcDao teacherJdbcDao;
    @Mock
    private TimePeriodJdbcDao timePeriodJdbcDao;
    @Mock
    private ClassroomJdbcDao classroomJdbcDao;
    @InjectMocks
    private LectureService lectureService;

    @Test
    public void addLectureDaoWasUsed() {
        lectureService.addLecture((long) 1, (long) 1, (long) 1, (long) 1);
        verify(subjectJdbcDao, times(1)).getById(any());
        verify(teacherJdbcDao, times(1)).getById(any());
        verify(timePeriodJdbcDao, times(1)).getById(any());
        verify(classroomJdbcDao, times(1)).getById(any());
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
        lectureService.updateLecture((long) 1, (long) 1, (long) 1, (long) 1, (long) 1, true);
        verify(subjectJdbcDao, times(1)).getById(any());
        verify(teacherJdbcDao, times(1)).getById(any());
        verify(timePeriodJdbcDao, times(1)).getById(any());
        verify(classroomJdbcDao, times(1)).getById(any());
        verify(lectureJdbcDao, times(1)).update(any());
    }

    @Test
    public void addGroupDaoWasUsed() {
        lectureService.addGroup((long) 1, (long) 1);
        verify(lectureJdbcDao, times(1)).addGroup(anyLong(), anyLong());
    }

    @Test
    public void getGroupsFromLectureDaoWasUsed() {
        lectureService.getGroupsFromLecture((long) 1);
        verify(lectureJdbcDao, times(1)).getGroupsFromLecture(anyLong());
    }
}
