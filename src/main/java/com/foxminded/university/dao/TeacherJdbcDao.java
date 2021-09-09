package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.TeacherDao;
import com.foxminded.university.dao.mappers.TeacherMapper;
import com.foxminded.university.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class TeacherJdbcDao implements TeacherDao {
    private static final String SQL_FIND_TEACHER = "SELECT * FROM teachers WHERE id = ?";
    private static final String SQL_UPDATE_TEACHER = "UPDATE teachers SET firstname = ?, lastname = ?, date_of_birth = ?," +
            " gender = ?, vacation_id = ?, working_hours_id = ?  WHERE id = ?";
    private static final String SQL_DELETE_TEACHER = "DELETE FROM teachers WHERE id = ?";
    private static final String SQL_INSERT_TEACHER = "INSERT INTO teachers(firstname, lastname, date_of_birth, gender," +
            " vacation_id, working_hours_id) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_ALL_TEACHER = "SELECT * FROM teachers";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    public TeacherJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Teacher getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_TEACHER, teacherMapper, id);
    }

    public List<Teacher> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_TEACHER, teacherMapper);
    }

    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_TEACHER, id);
    }

    public void update(Teacher teacher) {
        jdbcTemplate.update(SQL_UPDATE_TEACHER, teacher.getFirstname(), teacher.getLastname(), teacher.getDateOfBirth(),
                teacher.getGender(), teacher.getVacation().getId(), teacher.getWorkingHours().getId(), teacher.getId());
    }

    public void create(Teacher teacher) {
        jdbcTemplate.update(SQL_INSERT_TEACHER, teacher.getFirstname(), teacher.getLastname(), teacher.getDateOfBirth(),
                teacher.getGender(), teacher.getVacation().getId(), teacher.getWorkingHours().getId());
    }
}
