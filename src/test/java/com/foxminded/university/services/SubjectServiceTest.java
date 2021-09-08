package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.SubjectJdbcDao;
import com.foxminded.university.dao.TeacherJdbcDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {
    @Mock
    private SubjectJdbcDao subjectJdbcDao;
    @Mock
    private TeacherJdbcDao teacherJdbcDao;
    @InjectMocks
    private SubjectService subjectService;

    @Test
    public void addSubjectDaoWasUsed() {
        subjectService.addSubject("", "", (long) 1);
        verify(subjectJdbcDao, times(1)).create(any());
        verify(teacherJdbcDao, times(1)).getById(any());
    }

    @Test
    public void getSubjectByIdDaoWasUsed() {
        subjectService.getSubjectById((long) 1);
        verify(subjectJdbcDao, times(1)).getById(any());
    }

    @Test
    public void deleteSubjectByIdDaoWasUsed() {
        subjectService.deleteSubjectById((long) 1);
        verify(subjectJdbcDao, times(1)).delete(any());
    }

    @Test
    public void getAllSubjectsDaoWasUsed() {
        subjectService.getAllSubjects();
        verify(subjectJdbcDao, times(1)).getAll();
    }

    @Test
    public void updateSubjectDaoWasUsed() {
        subjectService.updateSubject("", "", (long) 1, (long) 1, true);
        verify(subjectJdbcDao, times(1)).update(any());
    }

    @Test
    public void addTeacherDaoWasUsed() {
        subjectService.addTeacher((long) 1, (long) 1);
        verify(subjectJdbcDao, times(1)).addTeacherToSubject(anyLong(), anyLong());
    }

    @Test
    public void getTeachersFromSubjectDaoWasUsed() {
        subjectService.getTeachersFromSubject((long) 1);
        verify(subjectJdbcDao, times(1)).getTeachersFromSubject(anyLong());
    }
}
