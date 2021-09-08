package com.foxminded.university.services;

import com.foxminded.university.dao.SubjectJdbcDao;
import com.foxminded.university.entities.Subject;
import com.foxminded.university.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private SubjectJdbcDao subjectJdbcDao;

    @Autowired
    public SubjectService(SubjectJdbcDao subjectJdbcDao) {
        this.subjectJdbcDao = subjectJdbcDao;
    }

    public void addSubject(Subject subject) {
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

    public void updateSubject(Subject subject) {
        subjectJdbcDao.update(subject);
    }

    public void addTeacher(Teacher teacher, Subject subject) {
        subjectJdbcDao.addTeacherToSubject(teacher.getId(), subject.getId());
    }

    public List<Teacher> getTeachersFromSubject(long subjectId) {
        return subjectJdbcDao.getTeachersFromSubject(subjectId);
    }
}
