package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.entities.Classroom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
class ClassroomServiceTest {
    @Mock
    private ClassroomJdbcDao classroomJdbcDaoMock;
    @Autowired
    private ClassroomJdbcDao classroomJdbcDao;
    @InjectMocks
    private ClassroomService classroomService;

    @Test
    void addClassroomDaoWasUsed() {
        doNothing().when(classroomJdbcDaoMock).create(any());
        classroomService.addClassroom(any());
        verify(classroomJdbcDaoMock, times(1)).create(any());
    }

    @Test
    void getClassroomByIdDaoWasUsed() {
        when(classroomJdbcDaoMock.getById(any())).thenReturn(any());
        classroomService.getClassroomById(1);
        verify(classroomJdbcDaoMock, times(1)).getById(any());
    }

    @Test
    void deleteClassroomByIdDaoWasUsed() {
        doNothing().when(classroomJdbcDaoMock).delete((long) 1);
        classroomService.deleteClassroomById(1);
        verify(classroomJdbcDaoMock, times(1)).delete(any());
    }

    @Test
    void getAllClassroomsDaoWasUsed() {
        when(classroomJdbcDaoMock.getAll()).thenReturn(new ArrayList<>());
        classroomService.getAllClassrooms();
        verify(classroomJdbcDaoMock, times(1)).getAll();
    }

    @Test
    void updateClassroomDaoWasUsed() {
        Classroom classroom = classroomJdbcDao.getById((long) 1);
        doNothing().when(classroomJdbcDaoMock).update(any());
        classroomService.updateClassroom(classroom);
        verify(classroomJdbcDaoMock, times(1)).update(any());
    }
}
