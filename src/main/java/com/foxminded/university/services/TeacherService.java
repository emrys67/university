package com.foxminded.university.services;

import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private TeacherJdbcDao teacherJdbcDao;

    @Autowired
    public TeacherService(TeacherJdbcDao teacherJdbcDao) {
        this.teacherJdbcDao = teacherJdbcDao;
    }

    public void addTeacher(Teacher teacher) {
        teacherJdbcDao.create(teacher);
    }

    public Teacher getTeacherById(long id) {
        return teacherJdbcDao.getById(id);
    }

    public void deleteTeacherById(long id) {
        teacherJdbcDao.delete(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherJdbcDao.getAll();
    }

    public void updateTeacher(Teacher teacher) {
        teacherJdbcDao.update(teacher);
    }
}
