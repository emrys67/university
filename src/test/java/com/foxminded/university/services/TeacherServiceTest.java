package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.entities.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    private TeacherJdbcDao teacherJdbcDaoMock;
    @Autowired
    private TeacherJdbcDao teacherJdbcDao;
    @InjectMocks
    private TeacherService teacherService;

    @Test
    void addTeacherDaoWasUsed() {
        teacherService.addTeacher(any());
        verify(teacherJdbcDaoMock, times(1)).create(any());
    }

    @Test
    void getTeacherByIdDaoWasUsed() {
        teacherService.getTeacherById((long) 1);
        verify(teacherJdbcDaoMock, times(1)).getById(any());
    }

    @Test
    void deleteTeacherByIdDaoWasUsed() {
        teacherService.deleteTeacherById((long) 1);
        verify(teacherJdbcDaoMock, times(1)).delete(any());
    }

    @Test
    void getAllTeachersDaoWasUsed() {
        teacherService.getAllTeachers();
        verify(teacherJdbcDaoMock, times(1)).getAll();
    }

    @Test
    void updateTeacherDaoWasUsed() {
        Teacher teacher = teacherJdbcDao.getById((long) 1);
        teacherService.updateTeacher(teacher);
        verify(teacherJdbcDaoMock, times(1)).update(any());
    }
}
