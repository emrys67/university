package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.VacationDao;
import com.foxminded.university.dao.mappers.VacationMapper;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class VacationJdbcDao implements VacationDao {
    private static final String SQL_FIND_VACATION = "SELECT * FROM vacations WHERE id = ?";
    private static final String SQL_UPDATE_VACATION = "UPDATE vacations SET description = ?, time_period_id = ? WHERE id = ?";
    private static final String SQL_DELETE_VACATION = "DELETE FROM vacations WHERE id = ?";
    private static final String SQL_INSERT_VACATION = "INSERT INTO vacations(description, time_period_id) VALUES(?, ?)";
    private static final String SQL_GET_ALL_VACATION = "SELECT * FROM vacations";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private VacationMapper vacationMapper;

    @Autowired
    public VacationJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Vacation getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_VACATION, vacationMapper, id);
    }

    public List<Vacation> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_VACATION, vacationMapper);
    }

    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_VACATION, id);
    }

    public void update(Vacation vacation) {
        jdbcTemplate.update(SQL_UPDATE_VACATION, vacation.getDescription(), vacation.getTimePeriod().getId(), vacation.getId());
    }

    public void create(Vacation vacation) {
        jdbcTemplate.update(SQL_INSERT_VACATION, vacation.getDescription(), vacation.getTimePeriod().getId());
    }
}
