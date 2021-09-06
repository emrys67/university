package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.TimePeriodDao;
import com.foxminded.university.entities.TimePeriod;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimePeriodJdbcDao implements TimePeriodDao {
    private final static String SQL_FIND_CLASSROOM = "";
    private final static String SQL_UPDATE_CLASSROOM = "";
    private final static String SQL_DELETE_CLASSROOM = "";
    private final static String SQL_INSERT_CLASSROOM = "";
    private final static String SQL_GET_ALL_CLASSROOM = "";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public TimePeriodJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public TimePeriod getById(Long id){
        return new TimePeriod();
    }

    public List<TimePeriod> getAll(){
        return new ArrayList<>();
    }

    public boolean delete(TimePeriod timePeriod){
        return true;
    }

    public boolean update(TimePeriod timePeriod){
        return true;
    }

    public boolean create(TimePeriod timePeriod){
        return true;
    }
}
