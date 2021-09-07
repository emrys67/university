package com.foxminded.university.services;

import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.entities.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassroomService {
    @Autowired
    ClassroomJdbcDao classroomJdbcDao;

    public void addClassroom(int capacity){
        classroomJdbcDao.create(new Classroom(capacity));
    }
    public void getClassroomById(){

    }
    public void deleteClassroomById(){

    }
    public void getAllClassrooms(){

    }
    public void updateClassroom(){

    }
}
