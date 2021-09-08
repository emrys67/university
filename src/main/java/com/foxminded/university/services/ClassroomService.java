package com.foxminded.university.services;

import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    private ClassroomJdbcDao classroomJdbcDao;

    @Autowired
    public ClassroomService(ClassroomJdbcDao classroomJdbcDao) {
        this.classroomJdbcDao = classroomJdbcDao;
    }

    public void addClassroom(Classroom classroom) {
        classroomJdbcDao.create(classroom);
    }

    public Classroom getClassroomById(long id) {
        return classroomJdbcDao.getById(id);
    }

    public void deleteClassroomById(long id) {
        classroomJdbcDao.delete(id);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomJdbcDao.getAll();
    }

    public void updateClassroom(Classroom classroom) {
        classroomJdbcDao.update(classroom);
    }
}
