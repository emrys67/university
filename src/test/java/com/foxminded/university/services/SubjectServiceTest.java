package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.SubjectJdbcDao;
import com.foxminded.university.entities.Subject;
import com.foxminded.university.entities.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    private SubjectJdbcDao subjectJdbcDao;
    @InjectMocks
    private SubjectService subjectService;
    @Mock
    Teacher teacher;
    @Mock
    Subject subject;

    @Test
    void addSubjectDaoWasUsed() {
        subjectService.addSubject(any());
        verify(subjectJdbcDao, times(1)).create(any());
    }

    @Test
    void getSubjectByIdDaoWasUsed() {
        subjectService.getSubjectById((long) 1);
        verify(subjectJdbcDao, times(1)).getById(any());
    }

    @Test
    void deleteSubjectByIdDaoWasUsed() {
        subjectService.deleteSubjectById((long) 1);
        verify(subjectJdbcDao, times(1)).delete(any());
    }

    @Test
    void getAllSubjectsDaoWasUsed() {
        subjectService.getAllSubjects();
        verify(subjectJdbcDao, times(1)).getAll();
    }

    @Test
    void updateSubjectDaoWasUsed() {
        subjectService.updateSubject(any());
        verify(subjectJdbcDao, times(1)).update(any());
    }

    @Test
    void addTeacherDaoWasUsed() {
        when(teacher.getId()).thenReturn((long) 1);
        when(subject.getId()).thenReturn((long) 1);
        subjectService.addTeacher(teacher, subject);
        verify(subjectJdbcDao, times(1)).addTeacherToSubject((long) 1, (long) 1);
    }

    @Test
    void getTeachersFromSubjectDaoWasUsed() {
        subjectService.getTeachersFromSubject((long) 1);
        verify(subjectJdbcDao, times(1)).getTeachersFromSubject(anyLong());
    }
}
