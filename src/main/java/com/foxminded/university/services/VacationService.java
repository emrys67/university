package com.foxminded.university.services;

import com.foxminded.university.dao.VacationJdbcDao;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Vacation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class VacationService {
    private static final Logger logger = LoggerFactory.getLogger(VacationService.class.getName());
    private VacationJdbcDao vacationJdbcDao;

    @Autowired
    public VacationService(VacationJdbcDao vacationJdbcDao) {
        this.vacationJdbcDao = vacationJdbcDao;
    }

    public void addVacation(Vacation vacation) {
        logger.info("Start service for adding vacation");
        try {
            vacationJdbcDao.create(vacation);
            logger.info("Vacation has been added successfully");
        } catch (DaoException exception) {
            String msg = "Vacation has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public Vacation getVacationById(long id) {
        logger.info("Start service for getting vacation by id {}", id);
        try {
            return vacationJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "Vacation has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteVacationById(long id) {
        logger.info("Start service for deleting vacation by id {}", id);
        try {
            vacationJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format("Vacation with id %s does not exist", id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        vacationJdbcDao.delete(id);
        logger.info("Vacation id {} has been deleted", id);
    }

    public List<Vacation> getAllVacations() {
        logger.info("Start service for getting all vacations");
        return vacationJdbcDao.getAll();
    }

    public void updateVacation(Vacation vacation) {
        logger.info("Start service for updating vacation with id {}", vacation.getId());
        try {
            vacationJdbcDao.getById(vacation.getId());
        } catch (DaoException exception) {
            String msg = format("Vacation with id %s does not exist", vacation.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            vacationJdbcDao.update(vacation);
        } catch (DaoException exception) {
            String msg = format("Vacation with id %s has not been updated", vacation.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }
}
