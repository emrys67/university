package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.LectureDao;
import com.foxminded.university.entities.Lecture;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class LectureJdbcDao implements LectureDao {
    private final static String SQL_FIND_CLASSROOM = "";
    private final static String SQL_UPDATE_CLASSROOM = "";
    private final static String SQL_DELETE_CLASSROOM = "";
    private final static String SQL_INSERT_CLASSROOM = "";
    private final static String SQL_GET_ALL_CLASSROOM = "";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public LectureJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Lecture getById(Long id){
        return new Lecture();
    }

    public List<Lecture> getAll(){
        return new ArrayList<>();
    }

    public boolean delete(Lecture lecture){
        return true;
    }

    public boolean update(Lecture lecture){
        return true;
    }

    public boolean create(Lecture lecture){
        return true;
    }
}
