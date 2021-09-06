package com.foxminded.university.dao;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.entities.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TeacherJdbcDaoTest {
    @Autowired
    private TeacherJdbcDao teacherJdbcDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void getById() {
        String firstname = teacherJdbcDao.getById((long) 1).getFirstname();
        assertEquals("Ilon", firstname);
    }

    @Test
    void getAll() {
        int size = teacherJdbcDao.getAll().size();
        assertEquals(5, size);
    }

    @Test
    void delete() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            teacherJdbcDao.delete((long) 1);
            teacherJdbcDao.getById((long) 1);
        });
    }

    @Test
    void update() {
        Teacher teacher = teacherJdbcDao.getById((long) 1);
        teacher.setId((long) 3);
        teacherJdbcDao.update(teacher);
        assertEquals("Ilon", teacherJdbcDao.getById((long) 3).getFirstname());
    }

    @Test
    void create() {
        Teacher teacher = teacherJdbcDao.getById((long) 1);
        teacherJdbcDao.create(teacher);
        assertEquals("Ilon", teacherJdbcDao.getById((long) 6).getFirstname());
    }
}
