package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.TimePeriodJdbcDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Date;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class TimePeriodServiceTest {
    @Mock
    private TimePeriodJdbcDao timePeriodJdbcDao;
    @InjectMocks
    private TimePeriodService timePeriodService;

    @Test
    public void addClassroomDaoWasUsed() {
        timePeriodService.addTimePeriod(new Date(1), new Date(1), LocalTime.NOON, LocalTime.NOON);
        verify(timePeriodJdbcDao, times(1)).create(any());
    }

    @Test
    public void getTimePeriodByIdDaoWasUsed() {
        timePeriodService.getTimePeriodById((long) 1);
        verify(timePeriodJdbcDao, times(1)).getById(any());
    }

    @Test
    public void deleteTimePeriodByIdDaoWasUsed() {
        timePeriodService.deleteTimePeriodById((long) 1);
        verify(timePeriodJdbcDao, times(1)).delete(any());
    }

    @Test
    public void getAllTimePeriodsDaoWasUsed() {
        timePeriodService.getAllTimePeriods();
        verify(timePeriodJdbcDao, times(1)).getAll();
    }

    @Test
    public void updateTimePeriodDaoWasUsed() {
        timePeriodService.updateTimePeriod((long) 1, new Date(1), new Date(1), LocalTime.NOON, LocalTime.NOON);
        verify(timePeriodJdbcDao, times(1)).update(any());
    }
}
