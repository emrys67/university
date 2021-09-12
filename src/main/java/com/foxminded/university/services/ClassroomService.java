package com.foxminded.university.services;

import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Classroom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class ClassroomService {
    private static final Logger logger = LoggerFactory.getLogger(ClassroomService.class.getName());
    private ClassroomJdbcDao classroomJdbcDao;

    @Autowired
    public ClassroomService(ClassroomJdbcDao classroomJdbcDao) {
        this.classroomJdbcDao = classroomJdbcDao;
    }

    public void addClassroom(Classroom classroom) {
        logger.info("Start service for adding classroom");
        try {
            classroomJdbcDao.create(classroom);
            logger.info("Classroom has been added successfully");
        } catch (DaoException exception) {
            String msg = "Classroom has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public Classroom getClassroomById(long id) {
        logger.info("Start service for getting classroom by id {}", id);
        try {
            return classroomJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "Classroom has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteClassroomById(long id) {
        logger.info("Start service for deleting classroom by id {}", id);
        try {
            classroomJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format("Classroom with id %s does not exist", id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        classroomJdbcDao.delete(id);
        logger.info("Classroom id {} has been deleted", id);
    }

    public List<Classroom> getAllClassrooms() {
        logger.info("Start service for getting all classrooms");
        return classroomJdbcDao.getAll();
    }

    public void updateClassroom(Classroom classroom) {
        logger.info("Start service for updating classroom with id {}", classroom.getId());
        try {
            classroomJdbcDao.getById(classroom.getId());
        } catch (DaoException exception) {
            String msg = format("Classroom with id %s does not exist", classroom.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            classroomJdbcDao.update(classroom);
        } catch (DaoException exception) {
            String msg = format("Classroom with id %s has not been updated", classroom.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }
}
