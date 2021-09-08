package com.foxminded.university.services;

import com.foxminded.university.dao.*;
import com.foxminded.university.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void addLecture(long subjectId, long teacherId, long timeperiodId, long classroomId) {
        Subject subject = subjectJdbcDao.getById(subjectId);
        Teacher teacher = teacherJdbcDao.getById(teacherId);
        TimePeriod timePeriod = timePeriodJdbcDao.getById(timeperiodId);
        Classroom classroom = classroomJdbcDao.getById(classroomId);
        Lecture lecture = new Lecture(subject, null, teacher, timePeriod, classroom);
        lectureJdbcDao.create(lecture);
    }

    public Lecture getLectureById(long id) {
        return lectureJdbcDao.getById(id);
    }

    public void deleteLectureById(long id) {
        lectureJdbcDao.delete(id);
    }

    public List<Lecture> getAllLectures() {
        return lectureJdbcDao.getAll();
    }

    public void updateLecture(long subjectId, long teacherId, long timeperiodId, long classroomId, long id, boolean changeGroups) {
        Subject subject = subjectJdbcDao.getById(subjectId);
        Teacher teacher = teacherJdbcDao.getById(teacherId);
        TimePeriod timePeriod = timePeriodJdbcDao.getById(timeperiodId);
        Classroom classroom = classroomJdbcDao.getById(classroomId);
        List<Group> list;
        if (changeGroups) {
            list = null;
        } else {
            list = lectureJdbcDao.getById(id).getGroups();
        }
        Lecture lecture = new Lecture(subject, list, teacher, timePeriod, classroom, id);
        lectureJdbcDao.update(lecture);
    }

    public void addGroup(long lectureId, long groupId) {
        lectureJdbcDao.addGroup(lectureId, groupId);
    }

    public List<Group> getGroupsFromLecture(long lectureId) {
        return lectureJdbcDao.getGroupsFromLecture(lectureId);
    }
}
