package com.foxminded.university.services;

import com.foxminded.university.dao.*;
import com.foxminded.university.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LectureService {
    @Autowired
    LectureJdbcDao lectureJdbcDao;
    @Autowired
    SubjectJdbcDao subjectJdbcDao;
    @Autowired
    TeacherJdbcDao teacherJdbcDao;
    @Autowired
    TimePeriodJdbcDao timePeriodJdbcDao;
    @Autowired
    ClassroomJdbcDao classroomJdbcDao;
    public void addLecture(long subjectId, long teacherId, long timeperiodId, long classroomId){
        Subject subject = subjectJdbcDao.getById(subjectId);
        Teacher teacher = teacherJdbcDao.getById(teacherId);
        TimePeriod timePeriod = timePeriodJdbcDao.getById(timeperiodId);
        Classroom classroom = classroomJdbcDao.getById(classroomId);
        Lecture lecture = new Lecture(subject, null, teacher, timePeriod, classroom);
        lectureJdbcDao.create(lecture);
    }
    public void getLectureById(){

    }
    public void deleteLectureById(){

    }
    public void getAllLectures(){

    }
    public void updateLecture(){

    }
    public void addGroup(){

    }
    public void getGroupsFromLecture(){

    }
}
