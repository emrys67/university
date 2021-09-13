package com.foxminded.university.dao;

import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.interfaces.SubjectDao;
import com.foxminded.university.dao.mappers.SubjectMapper;
import com.foxminded.university.dao.mappers.TeacherMapper;
import com.foxminded.university.entities.Subject;
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
public class SubjectJdbcDao implements SubjectDao {
    private static final String SQL_FIND_SUBJECT = "SELECT * FROM subjects WHERE id = ?";
    private static final String SQL_UPDATE_SUBJECT = "UPDATE subjects SET name = ?, description = ?, supervisor_id = ?" +
            " WHERE id = ?";
    private static final String SQL_DELETE_SUBJECT = "DELETE FROM subjects WHERE id = ?";
    private static final String SQL_INSERT_SUBJECT = "INSERT INTO subjects(name, description, supervisor_id) VALUES(?, ?, ?)";
    private static final String SQL_GET_ALL_SUBJECT = "SELECT * FROM subjects";
    private static final String SQL_GET_ALL_TEACHERS = "SELECT * FROM teachers JOIN subjects_teachers ON teachers.id = " +
            "subjects_teachers.teacher_id WHERE subject_id = ?";
    private static final String SQL_ADD_TEACHER = "INSERT INTO subjects_teachers(teacher_id, subject_id) VALUES (?, ?)";
    private static final String SQL_DELETE_TEACHER = "DELETE FROM subjects_teachers WHERE teacher_id = ? AND subject_id = ?";
    private static final Logger logger = LoggerFactory.getLogger(SubjectJdbcDao.class.getName());
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    public SubjectJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Subject getById(Long id) {
        Subject subject;
        logger.debug("Getting student by id {}", id);
        try {
            subject = jdbcTemplate.queryForObject(SQL_FIND_SUBJECT, subjectMapper, id);
        } catch (EmptyResultDataAccessException exception) {
            String msg = format("Couldn't find subject with id '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        } catch (DataAccessException exception) {
            String msg = format("Unable to get subject with ID '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        }
        return subject;
    }

    public List<Subject> getAll() {
        logger.debug("Getting all subjects");
        return jdbcTemplate.query(SQL_GET_ALL_SUBJECT, subjectMapper);
    }

    public void delete(Long id) {
        logger.debug("Deleting subject by id {}", id);
        jdbcTemplate.update(SQL_DELETE_SUBJECT, id);
    }

    public void update(Subject subject) {
        logger.info("Updating subject with id {}", subject.getId());
        List<Teacher> currentTeachers = getTeachersFromSubject(subject.getId());
        subject.getTeachers().stream().filter(teacher -> !currentTeachers.contains(teacher)).forEach(teacher -> addTeacherToSubject(teacher.getId(), subject.getId()));
        currentTeachers.stream().filter(teacher -> !subject.getTeachers().contains(teacher)).forEach(teacher -> deleteTeacherFromSubject(teacher.getId(), subject.getId()));
        boolean success = jdbcTemplate.update(SQL_UPDATE_SUBJECT, subject.getName(), subject.getDescription(), subject.getSupervisor().getId(),
                subject.getId()) > 0;
        if (success) {
            logger.debug("Subject with id {} has been updated", subject.getId());
        } else {
            String msg = format("Subject with id %s has not been updated", subject.getId());
            logger.warn(msg);
            throw new DaoException(msg);
        }
    }

    public void create(Subject subject) {
        logger.info("Start creating subject");
        if (subject == null) {
            String msg = "Cannot create subject, because subject is null";
            logger.warn(msg);
            throw new DaoException(msg);
        }
        jdbcTemplate.update(SQL_INSERT_SUBJECT, subject.getName(), subject.getDescription(), subject.getSupervisor().getId());
        logger.debug("Subject has been created");
    }

    public void addTeacherToSubject(long teacherId, long subjectId) {
        logger.debug("Adding teacher with id {} to the subject with id {}", teacherId, subjectId);
        jdbcTemplate.update(SQL_ADD_TEACHER, teacherId, subjectId);
    }

    public void deleteTeacherFromSubject(long teacherId, long subjectId) {
        logger.debug("Deleting teacher with id {} from subject with id {}", teacherId, subjectId);
        jdbcTemplate.update(SQL_DELETE_TEACHER, teacherId, subjectId);
    }

    public List<Teacher> getTeachersFromSubject(long subjectId) {
        logger.debug("Getting all teachers from the subject");
        return jdbcTemplate.query(SQL_GET_ALL_TEACHERS, teacherMapper, subjectId);
    }
}
