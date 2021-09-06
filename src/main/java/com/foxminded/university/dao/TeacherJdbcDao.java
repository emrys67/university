package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.TeacherDao;
import com.foxminded.university.entities.Teacher;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherJdbcDao implements TeacherDao {
    private final static String SQL_FIND_CLASSROOM = "";
    private final static String SQL_UPDATE_CLASSROOM = "";
    private final static String SQL_DELETE_CLASSROOM = "";
    private final static String SQL_INSERT_CLASSROOM = "";
    private final static String SQL_GET_ALL_CLASSROOM = "";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public TeacherJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Teacher getById(Long id){
        return new Teacher();
    }

    public List<Teacher> getAll(){
        return new ArrayList<>();
    }

    public boolean delete(Teacher teacher){
        return true;
    }

    public boolean update(Teacher teacher){
        return true;
    }

    public boolean create(Teacher teacher){
        return true;
    }
}
