package com.foxminded.university.dao;

import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.interfaces.TimePeriodDao;
import com.foxminded.university.dao.mappers.TimePeriodMapper;
import com.foxminded.university.entities.TimePeriod;
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
public class TimePeriodJdbcDao implements TimePeriodDao {
    private static final String SQL_FIND_TIME_PERIOD = "SELECT * FROM time_periods WHERE id = ?";
    private static final String SQL_UPDATE_TIME_PERIOD = "UPDATE time_periods SET start_time = ?, end_time = ?," +
            "start_date = ?, end_date = ? WHERE id = ?";
    private static final String SQL_DELETE_TIME_PERIOD = "DELETE FROM time_periods WHERE id = ?";
    private static final String SQL_INSERT_TIME_PERIOD = "INSERT INTO time_periods(start_time, end_time, start_date," +
            " end_date) VALUES(?, ?, ?, ?)";
    private static final String SQL_GET_ALL_TIME_PERIOD = "SELECT * FROM time_periods";
    private static final Logger logger = LoggerFactory.getLogger(TimePeriodJdbcDao.class.getName());
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TimePeriodMapper timePeriodMapper;

    @Autowired
    public TimePeriodJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public TimePeriod getById(Long id) {
        TimePeriod timePeriod;
        logger.info("Getting timePeriod by id {}", id);
        try {
            timePeriod = jdbcTemplate.queryForObject(SQL_FIND_TIME_PERIOD, timePeriodMapper, id);
        } catch (EmptyResultDataAccessException exception) {
            String msg = format("Couldn't find timePeriod with id '%s'", id);
            throw new DaoException(msg, exception);
        } catch (DataAccessException exception) {
            String msg = format("Unable to get timePeriod with ID '%s'", id);
            throw new DaoException(msg, exception);
        }
        return timePeriod;
    }

    public List<TimePeriod> getAll() {
        logger.info("Getting all timePeriods");
        return jdbcTemplate.query(SQL_GET_ALL_TIME_PERIOD, timePeriodMapper);
    }

    public void delete(Long id) {
        logger.info("Deleting timePeriod by id {}", id);
        jdbcTemplate.update(SQL_DELETE_TIME_PERIOD, id);
    }

    public void update(TimePeriod timePeriod) {
        logger.info("Updating timePeriod with id {}", timePeriod.getId());
        boolean success = jdbcTemplate.update(SQL_UPDATE_TIME_PERIOD, timePeriod.getStartTime(), timePeriod.getEndTime(), timePeriod.getStartDate(),
                timePeriod.getEndDate(), timePeriod.getId()) > 0;
        if (success) {
            logger.info("TimePeriod with id {} has been updated", timePeriod.getId());
        } else {
            String msg = format("TimePeriod with id %s has not been updated", timePeriod.getId());
            logger.warn(msg);
            throw new DaoException(msg);
        }
    }

    public void create(TimePeriod timePeriod) {
        logger.info("Creating timePeriod");
        try {
            jdbcTemplate.update(SQL_INSERT_TIME_PERIOD, timePeriod.getStartTime(), timePeriod.getEndTime(), timePeriod.getStartDate(),
                    timePeriod.getEndDate());
            logger.info("TimePeriod has been created");
        } catch (NullPointerException exception) {
            String msg = "Cannot create timePeriod, because timePeriod is null";
            logger.warn(msg);
            throw new DaoException(msg, exception);
        }
    }
}
