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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
class LectureServiceTest {
    @Mock
    private LectureJdbcDao lectureJdbcDaoMock;
    @Mock
    private GroupJdbcDao groupJdbcDaoMock;
    @InjectMocks
    private LectureService lectureService;
    @Autowired
    private LectureJdbcDao lectureJdbcDao;
    @Autowired
    private GroupJdbcDao groupJdbcDao;

    @Test
    void addLectureDaoWasUsed() {
        lectureService.addLecture(any());
        verify(lectureJdbcDaoMock, times(1)).create(any());
    }

    @Test
    void getLectureByIdDaoWasUsed() {
        lectureService.getLectureById((long) 1);
        verify(lectureJdbcDaoMock, times(1)).getById(any());
    }

    @Test
    void deleteLectureByIdDaoWasUsed() {
        lectureService.deleteLectureById((long) 1);
        verify(lectureJdbcDaoMock, times(1)).delete(any());
    }

    @Test
    void getAllLecturesDaoWasUsed() {
        lectureService.getAllLectures();
        verify(lectureJdbcDaoMock, times(1)).getAll();
    }

    @Test
    void updateLectureDaoWasUsed() {
        Lecture lecture = lectureJdbcDao.getById((long) 1);
        lectureService.updateLecture(lecture);
        verify(lectureJdbcDaoMock, times(1)).update(any());
    }

    @Test
    void addGroupDaoWasUsed() {
        Group group = groupJdbcDao.getById((long) 1);
        Lecture lecture = lectureJdbcDao.getById((long) 1);
        when(groupJdbcDaoMock.getById(anyLong())).thenReturn(group);
        lectureService.addGroup(lecture, group);
        verify(lectureJdbcDaoMock, times(1)).addGroup(anyLong(), anyLong());
    }

    @Test
    void getGroupsFromLectureDaoWasUsed() {
        lectureService.getGroupsFromLecture((long) 1);
        verify(lectureJdbcDaoMock, times(1)).getGroupsFromLecture(anyLong());
    }
}
