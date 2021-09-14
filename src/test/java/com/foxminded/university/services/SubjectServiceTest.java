package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.SubjectJdbcDao;
import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.entities.Subject;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {
    @Mock
    private SubjectJdbcDao subjectJdbcDaoMock;
    @Mock
    private TeacherJdbcDao teacherJdbcDaoMock;
    @Autowired
    private SubjectJdbcDao subjectJdbcDao;
    @Autowired
    private TeacherJdbcDao teacherJdbcDao;
    @InjectMocks
    private SubjectService subjectService;

    @Test
    void addSubjectDaoWasUsed() {
        Subject subject = subjectJdbcDao.getById((long) 1);
        subjectService.addSubject(subject);
        verify(subjectJdbcDaoMock, times(1)).create(any());
    }

    @Test
    void getSubjectByIdDaoWasUsed() {
        subjectService.getSubjectById((long) 1);
        verify(subjectJdbcDaoMock, times(1)).getById(any());
    }

    @Test
    void deleteSubjectByIdDaoWasUsed() {
        subjectService.deleteSubjectById((long) 1);
        verify(subjectJdbcDaoMock, times(1)).delete(any());
    }

    @Test
    void getAllSubjectsDaoWasUsed() {
        subjectService.getAllSubjects();
        verify(subjectJdbcDaoMock, times(1)).getAll();
    }

    @Test
    void updateSubjectDaoWasUsed() {
        Subject subject = subjectJdbcDao.getById((long) 1);
        subjectService.updateSubject(subject);
        verify(subjectJdbcDaoMock, times(1)).update(any());
    }

    @Test
    void addTeacherDaoWasUsed() {
        Subject subject = subjectJdbcDao.getById((long) 1);
        Teacher teacher = teacherJdbcDao.getById((long) 1);
        when(teacherJdbcDaoMock.getById(anyLong())).thenReturn(teacher);
        subjectService.addTeacher(teacher, subject);
        verify(subjectJdbcDaoMock, times(1)).addTeacherToSubject(anyLong(), anyLong());
    }

    @Test
    void getTeachersFromSubjectDaoWasUsed() {
        subjectService.getTeachersFromSubject((long) 1);
        verify(subjectJdbcDaoMock, times(1)).getTeachersFromSubject(anyLong());
    }
}
