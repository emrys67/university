package com.foxminded.university.dao;

import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.interfaces.TeacherDao;
import com.foxminded.university.dao.mappers.TeacherMapper;
import com.foxminded.university.entities.Teacher;
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
public class TeacherJdbcDao implements TeacherDao {
    private static final String SQL_FIND_TEACHER = "SELECT * FROM teachers WHERE id = ?";
    private static final String SQL_UPDATE_TEACHER = "UPDATE teachers SET firstname = ?, lastname = ?, date_of_birth = ?," +
            " gender = ?, vacation_id = ?, working_hours_id = ?  WHERE id = ?";
    private static final String SQL_DELETE_TEACHER = "DELETE FROM teachers WHERE id = ?";
    private static final String SQL_INSERT_TEACHER = "INSERT INTO teachers(firstname, lastname, date_of_birth, gender," +
            " vacation_id, working_hours_id) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_ALL_TEACHER = "SELECT * FROM teachers";
    private static final Logger logger = LoggerFactory.getLogger(TeacherJdbcDao.class.getName());
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    public TeacherJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Teacher getById(Long id) {
        Teacher teacher;
        logger.debug("Getting teacher by id {}", id);
        try {
            teacher = jdbcTemplate.queryForObject(SQL_FIND_TEACHER, teacherMapper, id);
        } catch (EmptyResultDataAccessException exception) {
            String msg = format("Couldn't find teacher with id '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        } catch (DataAccessException exception) {
            String msg = format("Unable to get teacher with ID '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        }
        return teacher;
    }

    public List<Teacher> getAll() {
        logger.debug("Getting all teachers");
        return jdbcTemplate.query(SQL_GET_ALL_TEACHER, teacherMapper);
    }

    public void delete(Long id) {
        logger.debug("Deleting teacher by id {}", id);
        jdbcTemplate.update(SQL_DELETE_TEACHER, id);
    }

    public void update(Teacher teacher) {
        logger.debug("Updating teacher with id {}", teacher.getId());
        boolean success = jdbcTemplate.update(SQL_UPDATE_TEACHER, teacher.getFirstname(), teacher.getLastname(), teacher.getDateOfBirth(),
                teacher.getGender(), teacher.getVacation().getId(), teacher.getWorkingHours().getId(), teacher.getId()) > 0;
        if (success) {
            logger.debug("Teacher with id {} has been updated", teacher.getId());
        } else {
            String msg = format("Teacher with id %s has not been updated", teacher.getId());
            logger.warn(msg);
            throw new DaoException(msg);
        }
    }

    public void create(Teacher teacher) {
        logger.info("Start creating teacher");
        if (teacher == null) {
            String msg = "Cannot create teacher, because teacher is null";
            logger.warn(msg);
            throw new DaoException(msg);
        }
        jdbcTemplate.update(SQL_INSERT_TEACHER, teacher.getFirstname(), teacher.getLastname(), teacher.getDateOfBirth(),
                teacher.getGender(), teacher.getVacation().getId(), teacher.getWorkingHours().getId());
        logger.debug("Teacher has been created");
    }
}
