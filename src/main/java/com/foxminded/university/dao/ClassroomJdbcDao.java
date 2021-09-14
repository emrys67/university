package com.foxminded.university.dao;

import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.interfaces.ClassroomDao;
import com.foxminded.university.dao.mappers.ClassroomMapper;
import com.foxminded.university.entities.Classroom;
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
public class ClassroomJdbcDao implements ClassroomDao {
    private static final String SQL_FIND_CLASSROOM = "SELECT * FROM classrooms WHERE id = ?";
    private static final String SQL_UPDATE_CLASSROOM = "UPDATE classrooms SET capacity = ? WHERE id = ?";
    private static final String SQL_DELETE_CLASSROOM = "DELETE FROM classrooms WHERE id = ?";
    private static final String SQL_INSERT_CLASSROOM = "INSERT INTO classrooms(capacity) VALUES(?)";
    private static final String SQL_GET_ALL_CLASSROOM = "SELECT * FROM classrooms";
    private static final Logger logger = LoggerFactory.getLogger(ClassroomJdbcDao.class.getName());
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    public ClassroomJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Classroom getById(Long id) {
        Classroom classroom;
        logger.debug("Getting classroom by id {}", id);
        try {
            classroom = jdbcTemplate.queryForObject(SQL_FIND_CLASSROOM, classroomMapper, id);
        } catch (EmptyResultDataAccessException exception) {
            String msg = format("Couldn't find student with id '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        } catch (DataAccessException exception) {
            String msg = format("Unable to get Student with ID '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        }
        return classroom;
    }

    public List<Classroom> getAll() {
        logger.debug("Getting all classrooms");
        return jdbcTemplate.query(SQL_GET_ALL_CLASSROOM, classroomMapper);
    }

    public void delete(Long id) {
        logger.debug("Deleting classroom with id {}", id);
        jdbcTemplate.update(SQL_DELETE_CLASSROOM, id);
    }

    public void update(Classroom classroom) {
        logger.info("Updating classroom with id {}", classroom.getId());
        boolean success = jdbcTemplate.update(SQL_UPDATE_CLASSROOM, classroom.getCapacity(), classroom.getId()) > 0;
        if (success) {
            logger.debug("Classroom with id {} has been updated", classroom.getId());
        } else {
            String msg = format("Classroom with id %s has not been updated", classroom.getId());
            logger.warn(msg);
            throw new DaoException(msg);
        }
    }

    public void create(Classroom classroom) {
        logger.debug("Start creating classroom");
        if (classroom == null) {
            String msg = "Cannot create classroom, because classroom is null";
            logger.warn(msg);
            throw new DaoException(msg);
        }
        logger.debug("Classroom has been created");
        jdbcTemplate.update(SQL_INSERT_CLASSROOM, classroom.getCapacity());
    }
}
