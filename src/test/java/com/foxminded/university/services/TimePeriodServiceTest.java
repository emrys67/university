package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.entities.TimePeriod;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Time;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class TimePeriodServiceTest {
    @Mock
    private TimePeriodJdbcDao timePeriodJdbcDaoMock;
    @Autowired
    private TimePeriodJdbcDao timePeriodJdbcDao;
    @InjectMocks
    private TimePeriodService timePeriodService;

    @Test
    void addTimePeriodDaoWasUsed() {
        TimePeriod timePeriod = timePeriodJdbcDao.getById((long) 1);
        timePeriodService.addTimePeriod(timePeriod);
        verify(timePeriodJdbcDaoMock, times(1)).create(any());
    }

    @Test
    void getTimePeriodByIdDaoWasUsed() {
        timePeriodService.getTimePeriodById((long) 1);
        verify(timePeriodJdbcDaoMock, times(1)).getById(any());
    }

    @Test
    void deleteTimePeriodByIdDaoWasUsed() {
        timePeriodService.deleteTimePeriodById((long) 1);
        verify(timePeriodJdbcDaoMock, times(1)).delete(any());
    }

    @Test
    void getAllTimePeriodsDaoWasUsed() {
        timePeriodService.getAllTimePeriods();
        verify(timePeriodJdbcDaoMock).getAll();
    }

    @Test
    void updateTimePeriodDaoWasUsed() {
        TimePeriod timePeriod = timePeriodJdbcDao.getById((long) 1);
        timePeriodService.updateTimePeriod(timePeriod);
        verify(timePeriodJdbcDaoMock, times(1)).update(any());
    }
}
