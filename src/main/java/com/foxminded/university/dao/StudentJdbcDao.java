package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.StudentDao;
import com.foxminded.university.dao.mappers.StudentMapper;
import com.foxminded.university.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class StudentJdbcDao implements StudentDao {
    private final static String SQL_FIND_STUDENT = "SELECT * FROM students WHERE id = ?";
    private final static String SQL_UPDATE_STUDENT = "UPDATE students SET firstname = ?, lastname = ?," +
            " date_of_birth = ?, gender = ?, study_year = ? WHERE id = ?";
    private final static String SQL_DELETE_STUDENT = "DELETE FROM students WHERE id = ?";
    private final static String SQL_INSERT_STUDENT = "INSERT INTO students(firstname, lastname, date_of_birth, gender," +
            " study_year) VALUES(?, ?, ?, ?, ?)";
    private final static String SQL_GET_ALL_STUDENT = "SELECT * FROM students";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    public StudentJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Student getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_STUDENT, studentMapper, id);
    }

    public List<Student> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_STUDENT, studentMapper);
    }

    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_STUDENT, id);
    }

    public void update(Student student) {
        jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getFirstname(), student.getLastname(), student.getDateOfBirth(),
                student.getGender(), student.getStudyYear(), student.getId());
    }

    public void create(Student student) {
        jdbcTemplate.update(SQL_INSERT_STUDENT, student.getFirstname(), student.getLastname(), student.getDateOfBirth(),
                student.getGender(), student.getStudyYear());
    }
}
