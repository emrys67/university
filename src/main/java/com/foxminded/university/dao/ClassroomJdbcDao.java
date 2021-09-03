package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.ClassroomDao;
import com.foxminded.university.entities.Classroom;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassroomJdbcDao implements ClassroomDao {
    private final static String SQL_FIND_CLASSROOM = "SELECT * FROM classrooms WHERE id = ?";
    private final static String SQL_UPDATE_CLASSROOM = "UPDATE people SET capacity = ? WHERE id = ?";
    private final static String SQL_DELETE_CLASSROOM = "DELETE FROM classrooms WHERE id = ?";
    private final static String SQL_INSERT_CLASSROOM = "INSERT INTO classrooms(capacity) VALUES(?)";
    private final static String SQL_GET_ALL_CLASSROOM = "SELECT * FROM classrooms";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public ClassroomJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Classroom getById(Long id){
        return new Classroom();
    }

    public List<Classroom> getAll(){
        return new ArrayList<>();
    }

    public boolean delete(Classroom classroom){
        return true;
    }

    public boolean update(Classroom classroom){
        return true;
    }

    public boolean create(Classroom classroom){
        return true;
    }
}
