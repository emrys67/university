package com.foxminded.university.dao;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.entities.Vacation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VacationJdbcDaoTest {
    @Autowired
    private VacationJdbcDao vacationJdbcDao;

    @Test
    void getById() {
        Vacation vacation = vacationJdbcDao.getById((long) 1);
        assertEquals("vacation description", vacation.getDescription());
    }

    @Test
    void getByWrongId() {
        Assertions.assertThrows(DaoException.class, () -> {
            vacationJdbcDao.getById((long) 20);
        });
    }

    @Test
    void getAll() {
        int size = vacationJdbcDao.getAll().size();
        assertEquals(6, size);
    }

    @Test
    void delete() {
        Assertions.assertThrows(DaoException.class, () -> {
            vacationJdbcDao.delete((long) 1);
            vacationJdbcDao.getById((long) 1);
        });
    }

    @Test
    void update() {
        Vacation vacation = vacationJdbcDao.getById((long) 1);
        vacation.setId(2);
        vacationJdbcDao.update(vacation);
        assertEquals(vacation.getTimePeriod(), vacationJdbcDao.getById((long) 2).getTimePeriod());
    }

    @Test
    void create() {
        Vacation vacation = vacationJdbcDao.getById((long) 1);
        vacationJdbcDao.create(vacation);
        assertEquals(vacation.getTimePeriod(), vacationJdbcDao.getById((long) 7).getTimePeriod());
    }

    @Test
    void createWithNullObject() {
        Assertions.assertThrows(DaoException.class, () -> {
            vacationJdbcDao.create(null);
        });
    }
}
