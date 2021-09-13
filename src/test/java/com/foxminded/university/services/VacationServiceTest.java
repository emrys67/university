package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.dao.VacationJdbcDao;
import com.foxminded.university.entities.Vacation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class VacationServiceTest {
    @Mock
    private VacationJdbcDao vacationJdbcDaoMock;
    @Mock
    private TimePeriodJdbcDao timePeriodJdbcDao;
    @Autowired
    private VacationJdbcDao vacationJdbcDao;
    @InjectMocks
    private VacationService vacationService;

    @Test
    void addVacationDaoWasUsed() {
        Vacation vacation = vacationJdbcDao.getById((long) 1);
        vacationService.addVacation(vacation);
        verify(vacationJdbcDaoMock, times(1)).create(any());
    }

    @Test
    void getVacationByIdDaoWasUsed() {
        vacationService.getVacationById(anyLong());
        verify(vacationJdbcDaoMock, times(1)).getById(any());
    }

    @Test
    void deleteVacationByIdDaoWasUsed() {
        vacationService.deleteVacationById(anyLong());
        verify(vacationJdbcDaoMock, times(1)).delete(any());
    }

    @Test
    void getAllVacationsDaoWasUsed() {
        vacationService.getAllVacations();
        verify(vacationJdbcDaoMock, times(1)).getAll();
    }

    @Test
    void updateVacationDaoWasUsed() {
        Vacation vacation = vacationJdbcDao.getById((long) 1);
        vacationService.updateVacation(vacation);
        verify(vacationJdbcDaoMock, times(1)).update(any());
    }
}
