package com.foxminded.university.dao;

import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.interfaces.StudentDao;
import com.foxminded.university.dao.mappers.StudentMapper;
import com.foxminded.university.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

import static java.lang.String.format;

@Component
public class StudentJdbcDao implements StudentDao {
    private static final String SQL_FIND_STUDENT = "SELECT * FROM students WHERE id = ?";
    private static final String SQL_UPDATE_STUDENT = "UPDATE students SET firstname = ?, lastname = ?," +
            " date_of_birth = ?, gender = ?, study_year = ? WHERE id = ?";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM students WHERE id = ?";
    private static final String SQL_INSERT_STUDENT = "INSERT INTO students(firstname, lastname, date_of_birth, gender," +
            " study_year) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_GET_ALL_STUDENT = "SELECT * FROM students";
    private static final Logger logger = LoggerFactory.getLogger(StudentJdbcDao.class.getName());
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    public StudentJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Student getById(Long id) {
        Student student;
        logger.debug("Getting student by id {}", id);
        try {
            student = jdbcTemplate.queryForObject(SQL_FIND_STUDENT, studentMapper, id);
        } catch (EmptyResultDataAccessException exception) {
            String msg = format("Couldn't find student with id '%s'", id);
            throw new DaoException(msg, exception);
        } catch (DataAccessException exception) {
            String msg = format("Unable to get student with ID '%s'", id);
            throw new DaoException(msg, exception);
        }
        return student;
    }

    public List<Student> getAll() {
        logger.debug("Getting all students");
        return jdbcTemplate.query(SQL_GET_ALL_STUDENT, studentMapper);
    }

    public void delete(Long id) {
        logger.debug("Deleting student by id {}", id);
        jdbcTemplate.update(SQL_DELETE_STUDENT, id);
    }

    public void update(Student student) {
        logger.debug("Updating student with id {}", student.getId());
        boolean success = jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getFirstname(), student.getLastname(), student.getDateOfBirth(),
                student.getGender(), student.getStudyYear(), student.getId()) > 0;
        if (success) {
            logger.debug("Student with id {} has been updated", student.getId());
        } else {
            String msg = format("Student with id %s has not been updated", student.getId());
            logger.warn(msg);
            throw new DaoException(msg);
        }
    }

    public void create(Student student) {
        logger.debug("Start creating student");
        if (student == null) {
            String msg = "Cannot create student, because student is null";
            logger.warn(msg);
            throw new DaoException(msg);
        }
        jdbcTemplate.update(SQL_INSERT_STUDENT, student.getFirstname(), student.getLastname(), student.getDateOfBirth(),
                student.getGender(), student.getStudyYear());
        logger.debug("Student has been created");
    }
}
