package com.foxminded.university.services;

import com.foxminded.university.dao.StudentJdbcDao;
import com.foxminded.university.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class StudentService {
    @Autowired
    StudentJdbcDao studentJdbcDao;

    public void addStudent(String firstname, String lastname, String gender, int studyYear, Date dateOfBith) {
        Student student = new Student(firstname, lastname, studyYear, dateOfBith, gender);
        studentJdbcDao.create(student);
    }

    public Student getStudentById(long id) {
        return studentJdbcDao.getById(id);
    }

    public void deleteStudentById(long id) {
        studentJdbcDao.delete(id);
    }

    public List<Student> getAllStudents() {
        return studentJdbcDao.getAll();
    }

    public void updateStudent(String firstname, String lastname, String gender, int studyYear, Date dateOfBith, long id) {
        Student student = new Student(firstname, lastname, studyYear, dateOfBith, gender, id);
        studentJdbcDao.update(student);
    }
}
