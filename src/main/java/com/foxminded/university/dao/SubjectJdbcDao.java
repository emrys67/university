package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.SubjectDao;
import com.foxminded.university.entities.Subject;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectJdbcDao implements SubjectDao {
    private final static String SQL_FIND_CLASSROOM = "";
    private final static String SQL_UPDATE_CLASSROOM = "";
    private final static String SQL_DELETE_CLASSROOM = "";
    private final static String SQL_INSERT_CLASSROOM = "";
    private final static String SQL_GET_ALL_CLASSROOM = "";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public SubjectJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Subject getById(Long id){
        return new Subject();
    }

    public List<Subject> getAll(){
        return new ArrayList<>();
    }

    public boolean delete(Subject subject){
        return true;
    }

    public boolean update(Subject subject){
        return true;
    }

    public boolean create(Subject subject){
        return true;
    }
}
