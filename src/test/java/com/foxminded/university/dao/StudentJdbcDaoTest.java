package com.foxminded.university.dao;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.entities.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentJdbcDaoTest {
    @Autowired
    private StudentJdbcDao studentJdbcDao;

    @Test
    void getById() {
        Student student = studentJdbcDao.getById((long) 1);
        assertEquals("Barbaris", student.getFirstname());
    }

    @Test
    void getAll() {
        int size = studentJdbcDao.getAll().size();
        assertEquals(10, size);
    }

    @Test
    void delete() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            studentJdbcDao.delete((long) 1);
            studentJdbcDao.getById((long) 1);
        });
    }

    @Test
    void update() {
        Student student = new Student("name", "lastname", 1, new Date((long) 1), "transformer", 1);
        studentJdbcDao.update(student);
        assertEquals("transformer", studentJdbcDao.getById((long) 1).getGender());
    }

    @Test
    void create() {
        Student student = new Student("name", "lastname", 1, new Date((long) 1), "transformer");
        studentJdbcDao.create(student);
        assertEquals("transformer", studentJdbcDao.getById((long) 11).getGender());
    }
}
