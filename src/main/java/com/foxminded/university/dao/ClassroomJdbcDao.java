package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.ClassroomDao;
import com.foxminded.university.dao.mappers.ClassroomMapper;
import com.foxminded.university.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ClassroomJdbcDao implements ClassroomDao {
    private final static String SQL_FIND_CLASSROOM = "SELECT * FROM classrooms WHERE id = ?";
    private final static String SQL_UPDATE_CLASSROOM = "UPDATE classrooms SET capacity = ? WHERE id = ?";
    private final static String SQL_DELETE_CLASSROOM = "DELETE FROM classrooms WHERE id = ?";
    private final static String SQL_INSERT_CLASSROOM = "INSERT INTO classrooms(capacity) VALUES(?)";
    private final static String SQL_GET_ALL_CLASSROOM = "SELECT * FROM classrooms";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    public ClassroomJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Classroom getById(Long id) {
        Classroom classroom = jdbcTemplate.queryForObject(SQL_FIND_CLASSROOM, classroomMapper, id);
        return classroom;
    }

    public List<Classroom> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_CLASSROOM, classroomMapper);
    }

    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_CLASSROOM, id);
    }

    public void update(Classroom classroom) {
        jdbcTemplate.update(SQL_UPDATE_CLASSROOM, classroom.getCapacity(), classroom.getId());
    }

    public void create(Classroom classroom) {
        jdbcTemplate.update(SQL_INSERT_CLASSROOM, classroom.getCapacity());
    }
}
