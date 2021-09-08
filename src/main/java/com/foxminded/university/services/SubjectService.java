package com.foxminded.university.services;

import com.foxminded.university.dao.SubjectJdbcDao;
import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.entities.Lecture;
import com.foxminded.university.entities.Subject;
import com.foxminded.university.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectService {
    @Autowired
    SubjectJdbcDao subjectJdbcDao;
    @Autowired
    TeacherJdbcDao teacherJdbcDao;

    public void addSubject(String name, String description, long teacherId) {
        Teacher teacher = teacherJdbcDao.getById(teacherId);
        Subject subject = new Subject(name, description, teacher, null);
        subjectJdbcDao.create(subject);
    }

    public Subject getSubjectById(long id) {
        return subjectJdbcDao.getById(id);
    }

    public void deleteSubjectById(long id) {
        subjectJdbcDao.delete(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectJdbcDao.getAll();
    }

    public void updateSubject(String name, String description, long teacherId, long id, boolean changeTeachers) {
        List<Teacher> list;
        Teacher teacher = teacherJdbcDao.getById(teacherId);
        if (changeTeachers) {
            list = null;
        } else {
            list = subjectJdbcDao.getTeachersFromSubject(id);
        }
        Subject subject = new Subject(name, description, teacher, list, id);
        subjectJdbcDao.update(subject);
    }

    public void addTeacher(long teacherId, long subjectId) {
        subjectJdbcDao.addTeacherToSubject(teacherId, subjectId);
    }

    public List<Teacher> getTeachersFromSubject(long subjectId) {
        return subjectJdbcDao.getTeachersFromSubject(subjectId);
    }
}
