package com.foxminded.university.dao;

import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.interfaces.VacationDao;
import com.foxminded.university.dao.mappers.VacationMapper;
import com.foxminded.university.entities.Vacation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

import static java.lang.String.format;

@Component
public class VacationJdbcDao implements VacationDao {
    private static final String SQL_FIND_VACATION = "SELECT * FROM vacations WHERE id = ?";
    private static final String SQL_UPDATE_VACATION = "UPDATE vacations SET description = ?, time_period_id = ? WHERE id = ?";
    private static final String SQL_DELETE_VACATION = "DELETE FROM vacations WHERE id = ?";
    private static final String SQL_INSERT_VACATION = "INSERT INTO vacations(description, time_period_id) VALUES(?, ?)";
    private static final String SQL_GET_ALL_VACATION = "SELECT * FROM vacations";
    private static final Logger logger = LoggerFactory.getLogger(VacationJdbcDao.class.getName());
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private VacationMapper vacationMapper;

    @Autowired
    public VacationJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Vacation getById(Long id) {
        Vacation vacation;
        logger.debug("Getting vacation by id {}", id);
        try {
            vacation = jdbcTemplate.queryForObject(SQL_FIND_VACATION, vacationMapper, id);
        } catch (EmptyResultDataAccessException exception) {
            String msg = format("Couldn't find vacation with id '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        } catch (DataAccessException exception) {
            String msg = format("Unable to get vacation with ID '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        }
        return vacation;
    }

    public List<Vacation> getAll() {
        logger.debug("Getting all vacations");
        return jdbcTemplate.query(SQL_GET_ALL_VACATION, vacationMapper);
    }

    public void delete(Long id) {
        logger.debug("Deleting vacation by id {}", id);
        jdbcTemplate.update(SQL_DELETE_VACATION, id);
    }

    public void update(Vacation vacation) {
        logger.debug("Updating vacation with id {}", vacation.getId());
        boolean success = jdbcTemplate.update(SQL_UPDATE_VACATION, vacation.getDescription(), vacation.getTimePeriod()
                .getId(), vacation.getId()) > 0;
        if (success) {
            logger.debug("Vacation with id {} has been updated", vacation.getId());
        } else {
            String msg = format("Vacation with id %s has not been updated", vacation.getId());
            logger.warn(msg);
            throw new DaoException(msg);
        }
    }

    public void create(Vacation vacation) {
        logger.debug("Start creating vacation");
        if (vacation == null) {
            String msg = "Cannot create vacation, because vacation is null";
            logger.warn(msg);
            throw new DaoException(msg);
        }
        jdbcTemplate.update(SQL_INSERT_VACATION, vacation.getDescription(), vacation.getTimePeriod().getId());
        logger.debug("Vacation has been created");
    }
}
