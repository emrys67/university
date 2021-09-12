package com.foxminded.university.services;

import com.foxminded.university.dao.SubjectJdbcDao;
import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Subject;
import com.foxminded.university.entities.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class SubjectService {
    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class.getName());
    private static final String SUBJECT_DOESNOT_EXIST = "Subject with id %s does not exist";
    private SubjectJdbcDao subjectJdbcDao;
    private TeacherJdbcDao teacherJdbcDao;

    @Autowired
    public SubjectService(SubjectJdbcDao subjectJdbcDao, TeacherJdbcDao teacherJdbcDao) {
        this.teacherJdbcDao = teacherJdbcDao;
        this.subjectJdbcDao = subjectJdbcDao;
    }

    public void addSubject(Subject subject) {
        logger.info("Start service for adding subject");
        try {
            subjectJdbcDao.create(subject);
            logger.info("Subject has been added successfully");
        } catch (DaoException exception) {
            String msg = "Subject has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public Subject getSubjectById(long id) {
        logger.info("Start service for getting subject by id {}", id);
        try {
            return subjectJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "Subject has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteSubjectById(long id) {
        logger.info("Start service for deleting subject by id {}", id);
        try {
            subjectJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format(SUBJECT_DOESNOT_EXIST, id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        subjectJdbcDao.delete(id);
        logger.info("Subject id {} has been deleted", id);
    }

    public List<Subject> getAllSubjects() {
        logger.info("Start service for getting all subjects");
        return subjectJdbcDao.getAll();
    }

    public void updateSubject(Subject subject) {
        logger.info("Start service for updating subject with id {}", subject.getId());
        try {
            subjectJdbcDao.getById(subject.getId());
        } catch (DaoException exception) {
            String msg = format(SUBJECT_DOESNOT_EXIST, subject.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            subjectJdbcDao.update(subject);
        } catch (DaoException exception) {
            String msg = format("Subject with id %s has not been updated", subject.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void addTeacher(Teacher teacher, Subject subject) {
        try {
            teacherJdbcDao.getById(teacher.getId());
        } catch (DaoException exception) {
            String msg = format("Teacher with id %s does not exist", teacher.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            subjectJdbcDao.getById(subject.getId());
        } catch (DaoException exception) {
            String msg = format(SUBJECT_DOESNOT_EXIST, subject.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        subjectJdbcDao.addTeacherToSubject(teacher.getId(), subject.getId());
    }

    public List<Teacher> getTeachersFromSubject(long subjectId) {
        try {
            subjectJdbcDao.getById(subjectId);
        } catch (DaoException exception) {
            String msg = format(SUBJECT_DOESNOT_EXIST, subjectId);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        return subjectJdbcDao.getTeachersFromSubject(subjectId);
    }
}
