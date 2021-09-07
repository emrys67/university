package com.foxminded.university.dao;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.entities.Subject;
import com.foxminded.university.entities.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SubjectJdbcDaoTest {
    @Autowired
    private SubjectJdbcDao subjectJdbcDao;
    @Autowired
    private TeacherJdbcDao teacherJdbcDao;

    @Test
    void getById() {
        Subject subject = subjectJdbcDao.getById((long) 1);
        assertEquals("math", subject.getName());
    }

    @Test
    void getByWrongId() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            subjectJdbcDao.getById((long) 20);
        });
    }

    @Test
    void getAll() {
        int size = subjectJdbcDao.getAll().size();
        assertEquals(3, size);
    }

    @Test
    void delete() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            subjectJdbcDao.delete((long) 1);
            subjectJdbcDao.getById((long) 1);
        });
    }

    @Test
    void update() {
        Teacher teacher = teacherJdbcDao.getById((long) 1);
        Subject subject = new Subject("name", "description", teacher, new ArrayList<>(), (long) 1);
        subjectJdbcDao.update(subject);
        assertEquals("name", subjectJdbcDao.getById((long) 1).getName());
    }

    @Test
    void create() {
        Teacher teacher = teacherJdbcDao.getById((long) 1);
        Subject subject = new Subject("name", "description", teacher, new ArrayList<>(), (long) 1);
        subjectJdbcDao.create(subject);
        assertEquals("name", subjectJdbcDao.getById((long) 4).getName());
    }

    @Test
    void createWithNullObject() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            subjectJdbcDao.create(null);
        });
    }

    @Test
    void addTeacherToSubject() {
        subjectJdbcDao.addTeacherToSubject((long) 1, (long) 3);
        assertTrue(subjectJdbcDao.getTeachersFromSubject((long) 3).stream().anyMatch(teacher -> teacher.getId() == (long) 1));
    }

    @Test
    void addNotExistingTeacherToSubject() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            subjectJdbcDao.addTeacherToSubject((long) 111, (long) 3);
        });
    }

    @Test
    void addTeacherToNotExistingSubject() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            subjectJdbcDao.addTeacherToSubject((long) 1, (long) 333);
        });
    }

    @Test
    void deleteTeacherFromSubject() {
        subjectJdbcDao.deleteTeacherFromSubject((long) 1, (long) 1);
        assertTrue(subjectJdbcDao.getTeachersFromSubject((long) 1).stream().noneMatch(teacher -> teacher.getId() == (long) 1));
    }

    @Test
    void getTeachersFromSubject() {
        int size = subjectJdbcDao.getTeachersFromSubject((long) 1).size();
        assertEquals(3, size);
    }
}
