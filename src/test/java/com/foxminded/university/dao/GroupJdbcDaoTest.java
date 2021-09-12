package com.foxminded.university.dao;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.entities.Classroom;
import com.foxminded.university.entities.Group;
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
public class GroupJdbcDaoTest {
    @Autowired
    private GroupJdbcDao groupJdbcDao;

    @Test
    void getAll() {
        int actual = groupJdbcDao.getAll().size();
        assertEquals(4, actual);
    }

    @Test
    void getById() {
        assertEquals(groupJdbcDao.getById((long) 1).getName(), "k-15");
    }

    @Test
    void getByWrongId() {
        Assertions.assertThrows(DaoException.class, () -> {
            groupJdbcDao.getById((long) 20);
        });
    }

    @Test
    void delete() {
        Assertions.assertThrows(DaoException.class, () -> {
            groupJdbcDao.delete((long) 1);
            groupJdbcDao.getById((long) 1);
        });
    }

    @Test
    void update() {
        Group group = new Group("00", new ArrayList<>(), (long) 1);
        groupJdbcDao.update(group);
        assertEquals(group, groupJdbcDao.getById((long) 1));
    }

    @Test
    void create() {
        Group group = new Group("tt", new ArrayList<>());
        groupJdbcDao.create(group);
        String actual = groupJdbcDao.getById((long) 5).getName();
        assertEquals(group.getName(), actual);
    }

    @Test
    void createWithNullObject() {
        Assertions.assertThrows(DaoException.class, () -> {
            groupJdbcDao.create(null);
        });
    }

    @Test
    void addStudentToGroup() {
        groupJdbcDao.addStudentToGroup(4, 1);
        assertTrue(groupJdbcDao.getStudentsFromGroup((long) 1).stream().anyMatch(student -> student.getId() == (long) 4));
    }

    @Test
    void addStudentToNotExistingGroup() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            groupJdbcDao.addStudentToGroup(4, 11);
        });
    }

    @Test
    void addNotExistingStudentToGroup() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            groupJdbcDao.addStudentToGroup(15, 1);
        });
    }

    @Test
    void deleteStudentFromGroup() {
        groupJdbcDao.deleteStudentFromGroup((long) 1, (long) 1);
        assertTrue(groupJdbcDao.getStudentsFromGroup((long) 1).stream().noneMatch(student -> student.getId() == (long) 1));
    }

    @Test
    void getStudentsFromGroup() {
        int actual = groupJdbcDao.getStudentsFromGroup((long) 1).size();
        assertEquals(3, actual);
    }
}
