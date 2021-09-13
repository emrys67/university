package com.foxminded.university.services;

import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class TeacherService {
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class.getName());
    private TeacherJdbcDao teacherJdbcDao;

    @Autowired
    public TeacherService(TeacherJdbcDao teacherJdbcDao) {
        this.teacherJdbcDao = teacherJdbcDao;
    }

    public void addTeacher(Teacher teacher) {
        logger.debug("Start service for adding teacher");
        if (teacher == null) {
            String msg = "Cannot create teacher, because teacher is null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        try {
            teacherJdbcDao.create(teacher);
            logger.debug("Teacher has been added successfully");
        } catch (DaoException exception) {
            String msg = "Teacher has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public Teacher getTeacherById(long id) {
        logger.debug("Start service for getting teacher by id {}", id);
        try {
            return teacherJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "Teacher has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteTeacherById(long id) {
        logger.debug("Start service for deleting teacher by id {}", id);
        try {
            teacherJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format("Teacher with id %s does not exist", id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        teacherJdbcDao.delete(id);
        logger.debug("Teacher id {} has been deleted", id);
    }

    public List<Teacher> getAllTeachers() {
        logger.debug("Start service for getting all teachers");
        return teacherJdbcDao.getAll();
    }

    public void updateTeacher(Teacher teacher) {
        logger.debug("Start service for updating teacher with id {}", teacher.getId());
        try {
            teacherJdbcDao.getById(teacher.getId());
        } catch (DaoException exception) {
            String msg = format("Teacher with id %s does not exist", teacher.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            teacherJdbcDao.update(teacher);
        } catch (DaoException exception) {
            String msg = format("Teacher with id %s has not been updated", teacher.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }
}
