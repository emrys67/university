package com.foxminded.university.dao;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LectureJdbcDaoTest {
    @Autowired
    private LectureJdbcDao lectureJdbcDao;
    @Autowired
    private SubjectJdbcDao subjectJdbcDao;
    @Autowired
    private ClassroomJdbcDao classroomJdbcDao;
    @Autowired
    private TeacherJdbcDao teacherJdbcDao;
    @Autowired
    private TimePeriodJdbcDao timePeriodJdbcDao;

    @Test
    void getById() {
        assertTrue(lectureJdbcDao.getById((long) 2).getSubject().getId() == (long) 1);
    }

    @Test
    void getByWrongId() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            lectureJdbcDao.getById((long) 123);
        });
    }

    @Test
    void getAll() {
        int actual = lectureJdbcDao.getAll().size();
        System.out.println(lectureJdbcDao.getAll().toString());
        assertEquals(3, actual);
    }

    @Test
    void delete() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            lectureJdbcDao.delete((long) 1);
            lectureJdbcDao.getById((long) 1);
        });
    }

    @Test
    void update() {
        Subject subject = subjectJdbcDao.getById((long) 1);
        TimePeriod timePeriod = timePeriodJdbcDao.getById((long) 1);
        Teacher teacher = teacherJdbcDao.getById((long) 1);
        Classroom classroom = classroomJdbcDao.getById((long) 1);
        Lecture lecture = new Lecture(subject, new ArrayList<>(), teacher, timePeriod, classroom, (long) 1);
        lectureJdbcDao.update(lecture);
        assertEquals(lecture, lectureJdbcDao.getById((long) 1));
    }

    @Test
    void create() {
        Subject subject = subjectJdbcDao.getById((long) 1);
        TimePeriod timePeriod = timePeriodJdbcDao.getById((long) 1);
        Teacher teacher = teacherJdbcDao.getById((long) 1);
        Classroom classroom = classroomJdbcDao.getById((long) 1);
        Lecture lecture = new Lecture(subject, new ArrayList<>(), teacher, timePeriod, classroom);
        lectureJdbcDao.create(lecture);
        assertEquals(lecture.getSubject(), lectureJdbcDao.getById((long) 4).getSubject());
    }

    @Test
    void createWithNullObject() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            subjectJdbcDao.create(null);
        });
    }

    @Test
    void addGroup() {
        lectureJdbcDao.addGroup((long) 1, (long) 4);
        assertTrue(lectureJdbcDao.getGroupsFromLecture((long) 1).stream().anyMatch(group -> group.getId() == (long) 4));
    }

    @Test
    void addNotExistingGroup() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            lectureJdbcDao.addGroup((long) 1, (long) 15);
        });
    }

    @Test
    void addGroupToNotExistingLecture() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            lectureJdbcDao.addGroup((long) 231, (long) 4);
        });
    }

    @Test
    void deleteGroup() {
        lectureJdbcDao.deleteGroup((long) 1, (long) 1);
        assertTrue(lectureJdbcDao.getGroupsFromLecture((long) 1).stream().noneMatch(group -> group.getId() == (long) 1));
    }

    @Test
    void getGroupsFromLecture() {
        int actual = lectureJdbcDao.getGroupsFromLecture((long) 1).size();
        assertEquals(3, actual);
    }
}
