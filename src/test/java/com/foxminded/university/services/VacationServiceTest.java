package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.dao.VacationJdbcDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class VacationServiceTest {
    @Mock
    private VacationJdbcDao vacationJdbcDao;
    @Mock
    private TimePeriodJdbcDao timePeriodJdbcDao;
    @InjectMocks
    private VacationService vacationService;

    @Test
    void addVacationDaoWasUsed() {
        vacationService.addVacation(any());
        verify(vacationJdbcDao, times(1)).create(any());
    }

    @Test
    void getVacationByIdDaoWasUsed() {
        vacationService.getVacationById(anyLong());
        verify(vacationJdbcDao, times(1)).getById(any());
    }

    @Test
    void deleteVacationByIdDaoWasUsed() {
        vacationService.deleteVacationById(anyLong());
        verify(vacationJdbcDao, times(1)).delete(any());
    }

    @Test
    void getAllVacationsDaoWasUsed() {
        vacationService.getAllVacations();
        verify(vacationJdbcDao, times(1)).getAll();
    }

    @Test
    void updateVacationDaoWasUsed() {
        vacationService.updateVacation(any());
        verify(vacationJdbcDao, times(1)).update(any());
    }
}
