package com.foxminded.university.dao;

import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.interfaces.GroupDao;
import com.foxminded.university.dao.mappers.GroupMapper;
import com.foxminded.university.dao.mappers.StudentMapper;
import com.foxminded.university.entities.Group;
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
public class GroupJdbcDao implements GroupDao {
    private static final String SQL_FIND_GROUP = "SELECT * FROM groups WHERE id = ?";
    private static final String SQL_UPDATE_GROUP = "UPDATE groups SET name = ? WHERE id = ?";
    private static final String SQL_DELETE_GROUP = "DELETE FROM groups WHERE id = ?";
    private static final String SQL_INSERT_GROUP = "INSERT INTO groups(name) VALUES(?)";
    private static final String SQL_GET_ALL_GROUP = "SELECT * FROM groups";
    private static final String SQL_INSERT_STUDENT = "INSERT INTO students_groups(student_id, group_id) VALUES(?, ?)";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM students_groups WHERE student_id = ? AND group_id = ?";
    private static final String SQL_GET_ALL_STUDENTS = "SELECT * FROM students JOIN students_groups ON student_id = students.id WHERE group_id = ?";
    private static final Logger logger = LoggerFactory.getLogger(GroupJdbcDao.class.getName());
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    StudentJdbcDao studentJdbcDao;
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    public GroupJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Group getById(Long id) {
        Group group;
        logger.debug("Getting group by id {}", id);
        try {
            group = jdbcTemplate.queryForObject(SQL_FIND_GROUP, groupMapper, id);
        } catch (EmptyResultDataAccessException exception) {
            String msg = format("Couldn't find group with id '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        } catch (DataAccessException exception) {
            String msg = format("Unable to get group with ID '%s'", id);
            logger.warn(msg);
            throw new DaoException(msg, exception);
        }
        return group;
    }

    public List<Group> getAll() {
        logger.debug("Getting all groups");
        return jdbcTemplate.query(SQL_GET_ALL_GROUP, groupMapper);
    }

    public void delete(Long id) {
        logger.debug("Deleting group with id {}", id);
        jdbcTemplate.update(SQL_DELETE_GROUP, id);
    }

    public void update(Group group) {
        logger.debug("Updating group with id {}", group.getId());
        List<Student> currentSutdents = getStudentsFromGroup(group.getId());
        group.getStudents().stream().filter(student -> !currentSutdents.contains(student)).forEach(student -> addStudentToGroup(student.getId(), group.getId()));
        currentSutdents.stream().filter(student -> !group.getStudents().contains(student)).forEach(student -> deleteStudentFromGroup(student.getId(), group.getId()));
        boolean success = jdbcTemplate.update(SQL_UPDATE_GROUP, group.getName(), group.getId()) > 0;
        if (success) {
            logger.debug("Group with id {} has been updated", group.getId());
        } else {
            String msg = format("Group with id %s has not been updated", group.getId());
            logger.warn(msg);
            throw new DaoException(msg);
        }
    }

    public void create(Group group) {
        logger.debug("Start creating group");
        if (group == null) {
            String msg = "Cannot create group, because group is null";
            logger.warn(msg);
            throw new DaoException(msg);
        }
        jdbcTemplate.update(SQL_INSERT_GROUP, group.getName());
        logger.debug("Group has been created");
    }

    public void addStudentToGroup(long studentId, long groupId) {
        logger.debug("Adding student with id {} to group with id {}", studentId, groupId);
        jdbcTemplate.update(SQL_INSERT_STUDENT, studentId, groupId);
    }

    public void deleteStudentFromGroup(long studentId, long groupId) {
        logger.debug("Deleting student id {} from group id {}", studentId, groupId);
        jdbcTemplate.update(SQL_DELETE_STUDENT, studentId, groupId);
    }

    public List<Student> getStudentsFromGroup(long groupId) {
        logger.debug("Getting all students from group id {}", groupId);
        return jdbcTemplate.query(SQL_GET_ALL_STUDENTS, studentMapper, groupId);
    }
}
