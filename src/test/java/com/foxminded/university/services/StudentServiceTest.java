package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.StudentJdbcDao;
import com.foxminded.university.entities.Student;
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
class StudentServiceTest {
    @Mock
    private StudentJdbcDao studentJdbcDaoMock;
    @Autowired
    private StudentJdbcDao studentJdbcDao;
    @InjectMocks
    private StudentService studentService;

    @Test
    void addStudentDaoWasUsed() {
        Student student = studentJdbcDao.getById((long) 1);
        studentService.addStudent(student);
        verify(studentJdbcDaoMock, times(1)).create(any());
    }

    @Test
    void getStudentByIdDaoWasUsed() {
        studentService.getStudentById((long) 1);
        verify(studentJdbcDaoMock, times(1)).getById(any());
    }

    @Test
    void deleteStudentByIdDaoWasUsed() {
        studentService.deleteStudentById((long) 1);
        verify(studentJdbcDaoMock, times(1)).delete(any());
    }

    @Test
    void getAllStudentsDaoWasUsed() {
        studentService.getAllStudents();
        verify(studentJdbcDaoMock, times(1)).getAll();
    }

    @Test
    void updateStudentDaoWasUsed() {
        Student student = studentJdbcDao.getById((long) 1);
        studentService.updateStudent(student);
        verify(studentJdbcDaoMock, times(1)).update(any());
    }
}
