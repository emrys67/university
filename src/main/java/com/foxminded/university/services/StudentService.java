package com.foxminded.university.services;

import com.foxminded.university.dao.StudentJdbcDao;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class.getName());
    private StudentJdbcDao studentJdbcDao;

    @Autowired
    public StudentService(StudentJdbcDao studentJdbcDao) {
        this.studentJdbcDao = studentJdbcDao;
    }

    public void addStudent(Student student) {
        logger.debug("Start service for adding student");
        if (student == null) {
            String msg = "Cannot create student, because student is null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        try {
            studentJdbcDao.create(student);
            logger.debug("Student has been added successfully");
        } catch (DaoException exception) {
            String msg = "Student has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public Student getStudentById(long id) {
        logger.debug("Start service for getting student by id {}", id);
        try {
            return studentJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "Student has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteStudentById(long id) {
        logger.debug("Start service for deleting student by id {}", id);
        try {
            studentJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format("Student with id %s does not exist", id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        studentJdbcDao.delete(id);
        logger.debug("Student id {} has been deleted", id);
    }

    public List<Student> getAllStudents() {
        logger.debug("Start service for getting all students");
        return studentJdbcDao.getAll();
    }

    public void updateStudent(Student student) {
        logger.debug("Start service for updating student with id {}", student.getId());
        try {
            studentJdbcDao.getById(student.getId());
        } catch (DaoException exception) {
            String msg = format("Student with id %s does not exist", student.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            studentJdbcDao.update(student);
        } catch (DaoException exception) {
            String msg = format("Student with id %s has not been updated", student.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }
}
