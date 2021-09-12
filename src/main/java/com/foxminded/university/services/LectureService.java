package com.foxminded.university.services;

import com.foxminded.university.dao.*;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.*;
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
        logger.info("Start service for adding lecture");
        try {
            lectureJdbcDao.create(lecture);
            logger.info("Lecture has been added successfully");
        } catch (DaoException exception) {
            String msg = "Lecture has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public Lecture getLectureById(long id) {
        logger.info("Start service for getting lecture by id {}", id);
        try {
            return lectureJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "Lecture has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteLectureById(long id) {
        logger.info("Start service for deleting lecture by id {}", id);
        try {
            lectureJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format(LECTURE_DOESNOT_EXIST, id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        lectureJdbcDao.delete(id);
        logger.info("Lecture id {} has been deleted", id);
    }

    public List<Lecture> getAllLectures() {
        logger.info("Start service for getting all lectures");
        return lectureJdbcDao.getAll();
    }

    public void updateLecture(Lecture lecture) {
        logger.info("Start service for updating lecture with id {}", lecture.getId());
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
