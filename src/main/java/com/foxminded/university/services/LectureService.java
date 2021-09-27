package com.foxminded.university.services;

import com.foxminded.university.dao.GroupJdbcDao;
import com.foxminded.university.dao.LectureJdbcDao;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Lecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class LectureService {
    private static final Logger logger = LoggerFactory.getLogger(LectureService.class.getName());
    private static final String LECTURE_DOESNOT_EXIST = "Lecture with id %s does not exist";
    private LectureJdbcDao lectureJdbcDao;
    private GroupJdbcDao groupJdbcDao;

    @Autowired
    public LectureService(LectureJdbcDao lectureJdbcDao, GroupJdbcDao groupJdbcDao) {
        this.groupJdbcDao = groupJdbcDao;
        this.lectureJdbcDao = lectureJdbcDao;
    }

    public void addLecture(Lecture lecture) {
        logger.debug("Start service for adding lecture");
        if (lecture == null) {
            String msg = "Cannot create lecture, because lecture is null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        try {
            lectureJdbcDao.create(lecture);
            logger.debug("Lecture has been added successfully");
        } catch (DaoException exception) {
            String msg = "Lecture has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public Lecture getLectureById(long id) {
        logger.debug("Start service for getting lecture by id {}", id);
        try {
            return lectureJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "Lecture has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteLectureById(long id) {
        logger.debug("Start service for deleting lecture by id {}", id);
        try {
            lectureJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format(LECTURE_DOESNOT_EXIST, id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        lectureJdbcDao.delete(id);
        logger.debug("Lecture id {} has been deleted", id);
    }

    public List<Lecture> getAllLectures() {
        logger.debug("Start service for getting all lectures");
        return lectureJdbcDao.getAll();
    }

    public void updateLecture(Lecture lecture) {
        logger.debug("Start service for updating lecture with id {}", lecture.getId());
        try {
            lectureJdbcDao.getById(lecture.getId());
        } catch (DaoException exception) {
            String msg = format(LECTURE_DOESNOT_EXIST, lecture.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            lectureJdbcDao.update(lecture);
        } catch (DaoException exception) {
            String msg = format("Lecture with id %s has not been updated", lecture.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void addGroup(Lecture lecture, Group group) {
        logger.debug("Start service for adding group to student");
        try {
            lectureJdbcDao.getById(lecture.getId());
        } catch (DaoException exception) {
            String msg = format(LECTURE_DOESNOT_EXIST, group.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            groupJdbcDao.getById(group.getId());
        } catch (DaoException exception) {
            String msg = format("Group with id %s does not exist", group.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        lectureJdbcDao.addGroup(lecture.getId(), group.getId());
    }

    public List<Group> getGroupsFromLecture(long lectureId) {
        logger.debug("Start service for getting all groups from the lecture");
        try {
            lectureJdbcDao.getById(lectureId);
        } catch (DaoException exception) {
            String msg = format(LECTURE_DOESNOT_EXIST, lectureId);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        return lectureJdbcDao.getGroupsFromLecture(lectureId);
    }
}
