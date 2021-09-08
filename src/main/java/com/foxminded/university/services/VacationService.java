package com.foxminded.university.services;

import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.dao.VacationJdbcDao;
import com.foxminded.university.entities.TimePeriod;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacationService {
    @Autowired
    VacationJdbcDao vacationJdbcDao;
    @Autowired
    TimePeriodJdbcDao timePeriodJdbcDao;

    public void addVacation(long timePeriodId, String description) {
        TimePeriod timePeriod = timePeriodJdbcDao.getById(timePeriodId);
        Vacation vacation = new Vacation(timePeriod, description);
        vacationJdbcDao.create(vacation);
    }

    public Vacation getVacationById(long id) {
        return vacationJdbcDao.getById(id);
    }

    public void deleteVacationById(long id) {
        vacationJdbcDao.delete(id);
    }

    public List<Vacation> getAllVacations() {
        return vacationJdbcDao.getAll();
    }

    public void updateVacation(long timePeriodId, String description, long id) {
        TimePeriod timePeriod = timePeriodJdbcDao.getById(timePeriodId);
        Vacation vacation = new Vacation(timePeriod, description, id);
        vacationJdbcDao.update(vacation);
    }
}
