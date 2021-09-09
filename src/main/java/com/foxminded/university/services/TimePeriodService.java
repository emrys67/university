package com.foxminded.university.services;

import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.entities.TimePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimePeriodService {
    private TimePeriodJdbcDao timePeriodJdbcDao;

    @Autowired
    public TimePeriodService(TimePeriodJdbcDao timePeriodJdbcDao) {
        this.timePeriodJdbcDao = timePeriodJdbcDao;
    }

    public void addTimePeriod(TimePeriod timePeriod) {
        timePeriodJdbcDao.create(timePeriod);
    }

    public TimePeriod getTimePeriodById(long id) {
        return timePeriodJdbcDao.getById(id);
    }

    public void deleteTimePeriodById(long id) {
        timePeriodJdbcDao.delete(id);
    }

    public List<TimePeriod> getAllTimePeriods() {
        return timePeriodJdbcDao.getAll();
    }

    public void updateTimePeriod(TimePeriod timePeriod) {
        timePeriodJdbcDao.update(timePeriod);
    }
}
