package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.StudentJdbcDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentJdbcDao studentJdbcDao;
    @InjectMocks
    private StudentService studentService;

    @Test
    public void addStudentDaoWasUsed() {
        studentService.addStudent("", "", "", 1, new Date(1));
        verify(studentJdbcDao, times(1)).create(any());
    }

    @Test
    public void getStudentByIdDaoWasUsed() {
        studentService.getStudentById((long) 1);
        verify(studentJdbcDao, times(1)).getById(any());
    }

    @Test
    public void deleteStudentByIdDaoWasUsed() {
        studentService.deleteStudentById((long) 1);
        verify(studentJdbcDao, times(1)).delete(any());
    }

    @Test
    public void getAllStudentsDaoWasUsed() {
        studentService.getAllStudents();
        verify(studentJdbcDao, times(1)).getAll();
    }

    @Test
    public void updateStudentDaoWasUsed() {
        studentService.updateStudent("", "", "", 1, new Date(1), (long) 1);
        verify(studentJdbcDao, times(1)).update(any());
    }
}
