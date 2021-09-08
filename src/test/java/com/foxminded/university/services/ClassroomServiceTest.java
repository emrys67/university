package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.ClassroomJdbcDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
public class ClassroomServiceTest {
    @Mock
    private ClassroomJdbcDao classroomJdbcDao;
    @InjectMocks
    private ClassroomService classroomService;

    @Test
    public void addClassroomDaoWasUsed() {
        doNothing().when(classroomJdbcDao).create(any());
        classroomService.addClassroom(any());
        verify(classroomJdbcDao, times(1)).create(any());
    }

    @Test
    public void getClassroomByIdDaoWasUsed() {
        when(classroomJdbcDao.getById(any())).thenReturn(any());
        classroomService.getClassroomById(1);
        verify(classroomJdbcDao, times(1)).getById(any());
    }

    @Test
    public void deleteClassroomByIdDaoWasUsed() {
        doNothing().when(classroomJdbcDao).delete(any());
        classroomService.deleteClassroomById(1);
        verify(classroomJdbcDao, times(1)).delete(any());
    }

    @Test
    public void getAllClassroomsDaoWasUsed() {
        when(classroomJdbcDao.getAll()).thenReturn(new ArrayList<>());
        classroomService.getAllClassrooms();
        verify(classroomJdbcDao, times(1)).getAll();
    }

    @Test
    public void updateClassroomDaoWasUsed() {
        doNothing().when(classroomJdbcDao).update(any());
        classroomService.updateClassroom(any());
        verify(classroomJdbcDao, times(1)).update(any());
    }
}
