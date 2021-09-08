package com.foxminded.university.services;

import com.foxminded.university.dao.*;
import com.foxminded.university.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {
    private LectureJdbcDao lectureJdbcDao;

    @Autowired
    public LectureService(LectureJdbcDao lectureJdbcDao) {
        this.lectureJdbcDao = lectureJdbcDao;
    }

    public void addLecture(Lecture lecture) {
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

    public void updateLecture(Lecture lecture) {

        lectureJdbcDao.update(lecture);
    }

    public void addGroup(Lecture lecture, Group group) {
        lectureJdbcDao.addGroup(lecture.getId(), group.getId());
    }

    public List<Group> getGroupsFromLecture(long lectureId) {
        return lectureJdbcDao.getGroupsFromLecture(lectureId);
    }
}
