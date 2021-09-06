package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.VacationDao;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class VacationJdbcDao implements VacationDao {
    private final static String SQL_FIND_CLASSROOM = "";
    private final static String SQL_UPDATE_CLASSROOM = "";
    private final static String SQL_DELETE_CLASSROOM = "";
    private final static String SQL_INSERT_CLASSROOM = "";
    private final static String SQL_GET_ALL_CLASSROOM = "";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public VacationJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Vacation getById(Long id){
        return new Vacation();
    }

    public List<Vacation> getAll(){
        return new ArrayList<>();
    }

    public boolean delete(Vacation vacation){
        return true;
    }

    public boolean update(Vacation vacation){
        return true;
    }

    public boolean create(Vacation vacation){
        return true;
    }
}
