package com.foxminded.university.dao;

import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.interfaces.LectureDao;
import com.foxminded.university.dao.mappers.GroupMapper;
import com.foxminded.university.dao.mappers.LectureMapper;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Lecture;
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
public class LectureJdbcDao implements LectureDao {
    private static final String SQL_FIND_LECTURE = "SELECT * FROM lectures WHERE id = ?";
    private static final String SQL_UPDATE_LECTURE = "UPDATE lectures SET subject_id = ?, teacher_id = ?, time_period_id" +
            " = ?, classroom_id = ? WHERE id = ?";
    private static final String SQL_DELETE_LECTURE = "DELETE FROM lectures WHERE id = ?";
    private static final String SQL_INSERT_LECTURE = "INSERT INTO lectures(teacher_id, time_period_id, classroom_id, subject_id) VALUES(?, ?, ?, ?)";
    private static final String SQL_GET_ALL_LECTURE = "SELECT * FROM lectures";
    private static final String SQL_GET_ALL_GROUPS = "SELECT * FROM groups JOIN lectures_groups ON groups.id = lectures_groups.group_id WHERE lecture_id = ?";
    private static final String SQL_ADD_GROUP = "INSERT INTO lectures_groups(lecture_id, group_id) VALUES (?, ?)";
    private static final String SQL_DELETE_GROUP = "DELETE FROM lectures_groups WHERE lecture_id = ? AND group_id = ?";
    private static final Logger logger = LoggerFactory.getLogger(LectureJdbcDao.class.getName());
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LectureMapper lectureMapper;
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    public LectureJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Lecture getById(Long id) {
        Lecture lecture;
        logger.info("Getting lecture by id {}", id);
        try {
            lecture = jdbcTemplate.queryForObject(SQL_FIND_LECTURE, lectureMapper, id);
        } catch (EmptyResultDataAccessException exception) {
            String msg = format("Couldn't find lecture with id '%s'", id);
            throw new DaoException(msg, exception);
        } catch (DataAccessException exception) {
            String msg = format("Unable to get lecture with ID '%s'", id);
            throw new DaoException(msg, exception);
        }
        return lecture;
    }

    public List<Lecture> getAll() {
        logger.info("Getting all lectures");
        return jdbcTemplate.query(SQL_GET_ALL_LECTURE, lectureMapper);
    }

    public void delete(Long id) {
        logger.info("Deleting lecture by id {}", id);
        jdbcTemplate.update(SQL_DELETE_LECTURE, id);
    }

    public void update(Lecture lecture) {
        logger.info("Updating lecture with id {}", lecture.getId());
        List<Group> currentGroups = getGroupsFromLecture(lecture.getId());
        lecture.getGroups().stream().filter(group -> !currentGroups.contains(group)).forEach(group -> addGroup(group.getId(), lecture.getId()));
        currentGroups.stream().filter(group -> !lecture.getGroups().contains(group)).forEach(group -> deleteGroup(group.getId(), lecture.getId()));
        boolean success = jdbcTemplate.update(SQL_UPDATE_LECTURE, lecture.getSubject().getId(), lecture.getTeacher().getId(),
                lecture.getTimePeriod().getId(), lecture.getClassroom().getId(), lecture.getId()) > 0;
        if (success) {
            logger.info("Lecture with id {} has been updated", lecture.getId());
        } else {
            String msg = format("Lecture with id %s has not been updated", lecture.getId());
            logger.warn(msg);
            throw new DaoException(msg);
        }
    }

    public void create(Lecture lecture) {
        logger.info("Creating lecture");
        try {
            jdbcTemplate.update(SQL_INSERT_LECTURE, lecture.getTeacher().getId(), lecture.getTimePeriod().getId(),
                    lecture.getClassroom().getId(), lecture.getSubject().getId());
            logger.info("Lecture has been created");
        } catch (NullPointerException exception) {
            String msg = "Cannot create lecture, because lecture is null";
            logger.warn(msg);
            throw new DaoException(msg, exception);
        }
    }

    public void addGroup(long lectureId, long groupId) {
        logger.info("Adding group with id {} to the lecture with id {}", groupId, lectureId);
        jdbcTemplate.update(SQL_ADD_GROUP, lectureId, groupId);
    }

    public void deleteGroup(long lectureId, long groupId) {
        logger.info("Deleting group with id {} from lecture with id {}", groupId, lectureId);
        jdbcTemplate.update(SQL_DELETE_GROUP, lectureId, groupId);
    }

    public List<Group> getGroupsFromLecture(long lectureId) {
        logger.info("Getting all groups from the lecture with id {}", lectureId);
        return jdbcTemplate.query(SQL_GET_ALL_GROUPS, groupMapper, lectureId);
    }
}
