package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.StudentDao;
import com.foxminded.university.entities.Student;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentJdbcDao implements StudentDao {
    private final static String SQL_FIND_CLASSROOM = "";
    private final static String SQL_UPDATE_CLASSROOM = "";
    private final static String SQL_DELETE_CLASSROOM = "";
    private final static String SQL_INSERT_CLASSROOM = "";
    private final static String SQL_GET_ALL_CLASSROOM = "";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public StudentJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Student getById(Long id){
        return new Student();
    }

    public List<Student> getAll(){
        return new ArrayList<>();
    }

    public boolean delete(Student student){
        return true;
    }

    public boolean update(Student student){
        return true;
    }

    public boolean create(Student student){
        return true;
    }
}
