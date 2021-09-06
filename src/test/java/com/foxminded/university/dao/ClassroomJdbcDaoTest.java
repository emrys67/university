package com.foxminded.university.dao;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.entities.Classroom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClassroomJdbcDaoTest {
    @Autowired
    private ClassroomJdbcDao classroomJdbcDao;

    @Test
    void getById() {
        Classroom classroom = new Classroom(50, 1);
        Classroom actual = classroomJdbcDao.getById((long) 1);
        assertEquals(classroom, actual);
    }

    @Test
    void getAll() {
        int actual = classroomJdbcDao.getAll().size();
        assertEquals(5, actual);
    }

    @Test
    void delete() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            classroomJdbcDao.delete((long) 1);
            classroomJdbcDao.getById((long) 1);
        });
    }

    @Test
    void update() {
        Classroom classroom = new Classroom(15, 2);
        classroomJdbcDao.update(classroom);
        assertEquals(15,classroomJdbcDao.getById((long) 2).getCapacity());
    }

    @Test
    void create() {
        Classroom classroom = new Classroom(200);
        classroomJdbcDao.create(classroom);
        assertEquals(200, classroomJdbcDao.getById((long) 6).getCapacity());
    }
}
