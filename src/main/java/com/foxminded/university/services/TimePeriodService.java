package com.foxminded.university.services;

import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.entities.TimePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Component
public class TimePeriodService {
    @Autowired
    TimePeriodJdbcDao timePeriodJdbcDao;

    public void addTimePeriod(Date startDate, Date endDate, LocalTime startTime, LocalTime endTime) {
        TimePeriod timePeriod = new TimePeriod(startDate, endDate, startTime, endTime);
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

    public void updateTimePeriod(long id, Date startDate, Date endDate, LocalTime startTime, LocalTime endTime) {
        TimePeriod timePeriod = new TimePeriod(id, startDate, endDate, startTime, endTime);
        timePeriodJdbcDao.update(timePeriod);
    }
}
