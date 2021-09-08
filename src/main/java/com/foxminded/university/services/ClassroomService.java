package com.foxminded.university.services;

import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassroomService {
    @Autowired
    ClassroomJdbcDao classroomJdbcDao;

    public void addClassroom(int capacity) {
        classroomJdbcDao.create(new Classroom(capacity));
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

    public void updateClassroom(int capacity, long id) {
        Classroom classroom = new Classroom(capacity, id);
        classroomJdbcDao.update(classroom);
    }
}
