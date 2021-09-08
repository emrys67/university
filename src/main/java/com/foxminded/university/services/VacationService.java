package com.foxminded.university.services;

import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.dao.VacationJdbcDao;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {
    private VacationJdbcDao vacationJdbcDao;
    private TimePeriodJdbcDao timePeriodJdbcDao;

    @Autowired
    public VacationService(VacationJdbcDao vacationJdbcDao, TimePeriodJdbcDao timePeriodJdbcDao) {
        this.vacationJdbcDao = vacationJdbcDao;
        this.timePeriodJdbcDao = timePeriodJdbcDao;
    }

    public void addVacation(Vacation vacation) {
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

    public void updateVacation(Vacation vacation) {
        vacationJdbcDao.update(vacation);
    }
}
