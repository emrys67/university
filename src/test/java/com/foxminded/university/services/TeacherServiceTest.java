package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.dao.VacationJdbcDao;
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
public class TeacherServiceTest {
    @Mock
    private TeacherJdbcDao teacherJdbcDao;
    @Mock
    private TimePeriodJdbcDao timePeriodJdbcDao;
    @Mock
    private VacationJdbcDao vacationJdbcDao;
    @InjectMocks
    private TeacherService teacherService;

    @Test
    public void addTeacherDaoWasUsed() {
        teacherService.addTeacher("", "", "", new Date(1), (long) 1, (long) 1);
        verify(timePeriodJdbcDao, times(1)).getById(any());
        verify(vacationJdbcDao, times(1)).getById(any());
        verify(teacherJdbcDao, times(1)).create(any());
    }

    @Test
    public void getTeacherByIdDaoWasUsed() {
        teacherService.getTeacherById((long) 1);
        verify(teacherJdbcDao, times(1)).getById(any());
    }

    @Test
    public void deleteTeacherByIdDaoWasUsed() {
        teacherService.deleteTeacherById((long) 1);
        verify(teacherJdbcDao, times(1)).delete(any());
    }

    @Test
    public void getAllTeachersDaoWasUsed() {
        teacherService.getAllTeachers();
        verify(teacherJdbcDao, times(1)).getAll();
    }

    @Test
    public void updateTeacherDaoWasUsed() {
        teacherService.updateTeacher("", "", "", new Date(1), (long) 1, (long) 1, (long) 1);
        verify(timePeriodJdbcDao, times(1)).getById(any());
        verify(vacationJdbcDao, times(1)).getById(any());
        verify(teacherJdbcDao, times(1)).update(any());
    }
}
