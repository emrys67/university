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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class VacationServiceTest {
    @Mock
    private VacationJdbcDao vacationJdbcDao;
    @Mock
    private TimePeriodJdbcDao timePeriodJdbcDao;
    @InjectMocks
    private VacationService vacationService;

    @Test
    public void addVacationDaoWasUsed() {
        vacationService.addVacation(anyLong(), "");
        verify(vacationJdbcDao, times(1)).create(any());
        verify(timePeriodJdbcDao, times(1)).getById(any());
    }

    @Test
    public void getVacationByIdDaoWasUsed() {
        vacationService.getVacationById(anyLong());
        verify(vacationJdbcDao, times(1)).getById(any());
    }

    @Test
    public void deleteVacationByIdDaoWasUsed() {
        vacationService.deleteVacationById(anyLong());
        verify(vacationJdbcDao, times(1)).delete(any());
    }

    @Test
    public void getAllVacationsDaoWasUsed() {
        vacationService.getAllVacations();
        verify(vacationJdbcDao, times(1)).getAll();
    }

    @Test
    public void updateVacationDaoWasUsed() {
        vacationService.updateVacation((long) 1, " ", (long) 1);
        verify(vacationJdbcDao, times(1)).update(any());
    }
}
