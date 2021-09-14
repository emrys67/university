package com.foxminded.university.services;

import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.TimePeriod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class TimePeriodService {
    private static final Logger logger = LoggerFactory.getLogger(TimePeriodService.class.getName());
    private TimePeriodJdbcDao timePeriodJdbcDao;

    @Autowired
    public TimePeriodService(TimePeriodJdbcDao timePeriodJdbcDao) {
        this.timePeriodJdbcDao = timePeriodJdbcDao;
    }

    public void addTimePeriod(TimePeriod timePeriod) {
        logger.debug("Start service for adding timePeriod");
        if (timePeriod == null) {
            String msg = "Cannot create timePeriod, because timePeriod is null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        try {
            timePeriodJdbcDao.create(timePeriod);
            logger.debug("TimePeriod has been added successfully");
        } catch (DaoException exception) {
            String msg = "TimePeriod has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public TimePeriod getTimePeriodById(long id) {
        logger.debug("Start service for getting timePeriod by id {}", id);
        try {
            return timePeriodJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "TimePeriod has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteTimePeriodById(long id) {
        logger.debug("Start service for deleting timePeriod by id {}", id);
        try {
            timePeriodJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format("TimePeriod with id %s does not exist", id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        timePeriodJdbcDao.delete(id);
        logger.debug("TimePeriod id {} has been deleted", id);
    }

    public List<TimePeriod> getAllTimePeriods() {
        logger.debug("Start service for getting all timePeriods");
        return timePeriodJdbcDao.getAll();
    }

    public void updateTimePeriod(TimePeriod timePeriod) {
        logger.debug("Start service for updating timePeriod with id {}", timePeriod.getId());
        try {
            timePeriodJdbcDao.getById(timePeriod.getId());
        } catch (DaoException exception) {
            String msg = format("TimePeriod with id %s does not exist", timePeriod.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            timePeriodJdbcDao.update(timePeriod);
        } catch (DaoException exception) {
            String msg = format("TimePeriod with id %s has not been updated", timePeriod.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }
}
