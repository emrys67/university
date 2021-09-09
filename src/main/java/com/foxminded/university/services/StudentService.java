package com.foxminded.university.services;

import com.foxminded.university.dao.StudentJdbcDao;
import com.foxminded.university.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentJdbcDao studentJdbcDao;

    @Autowired
    public StudentService(StudentJdbcDao studentJdbcDao) {
        this.studentJdbcDao = studentJdbcDao;
    }

    public void addStudent(Student student) {
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

    public void updateStudent(Student student) {
        studentJdbcDao.update(student);
    }
}
