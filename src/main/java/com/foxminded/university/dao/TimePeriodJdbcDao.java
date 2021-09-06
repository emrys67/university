package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.TimePeriodDao;
import com.foxminded.university.dao.mappers.TimePeriodMapper;
import com.foxminded.university.entities.TimePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class TimePeriodJdbcDao implements TimePeriodDao {
    private final static String SQL_FIND_TIME_PERIOD = "SELECT * FROM time_periods WHERE id = ?";
    private final static String SQL_UPDATE_TIME_PERIOD = "UPDATE time_periods SET start_time = ?, end_time = ?," +
            "start_date = ?, end_date = ? WHERE id = ?";
    private final static String SQL_DELETE_TIME_PERIOD = "DELETE FROM time_periods WHERE id = ?";
    private final static String SQL_INSERT_TIME_PERIOD = "INSERT INTO time_periods(start_time, end_time, start_date," +
            " end_date) VALUES(?, ?, ?, ?)";
    private final static String SQL_GET_ALL_TIME_PERIOD = "SELECT * FROM time_periods";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TimePeriodMapper timePeriodMapper;

    @Autowired
    public TimePeriodJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public TimePeriod getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_TIME_PERIOD, timePeriodMapper, id);
    }

    public List<TimePeriod> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_TIME_PERIOD, timePeriodMapper);
    }

    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_TIME_PERIOD, id);
    }

    public void update(TimePeriod timePeriod) {
        jdbcTemplate.update(SQL_UPDATE_TIME_PERIOD, timePeriod.getStartTime(), timePeriod.getEndTime(), timePeriod.getStartDate(),
                timePeriod.getEndDate(), timePeriod.getId());
    }

    public void create(TimePeriod timePeriod) {
        jdbcTemplate.update(SQL_INSERT_TIME_PERIOD, timePeriod.getStartTime(), timePeriod.getEndTime(), timePeriod.getStartDate(),
                timePeriod.getEndDate());
    }
}
