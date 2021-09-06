package com.foxminded.university.dao;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.entities.TimePeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TimePeriodJdbcDaoTest {
    @Autowired
    private TimePeriodJdbcDao timePeriodJdbcDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void getById() {
        TimePeriod timePeriod = timePeriodJdbcDao.getById((long) 1);
        assertEquals(LocalTime.of(12, 15, 0), timePeriod.getStartTime());
    }

    @Test
    void getAll() {
        int size = timePeriodJdbcDao.getAll().size();
        assertEquals(10, size);
    }

    @Test
    void delete() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            timePeriodJdbcDao.delete((long) 1);
            timePeriodJdbcDao.getById((long) 1);
        });
    }

    @Test
    void update() {
        TimePeriod timePeriod = timePeriodJdbcDao.getById((long) 1);
        timePeriod.setId((long) 3);
        timePeriodJdbcDao.update(timePeriod);
        assertEquals(timePeriod.getStartTime(), timePeriodJdbcDao.getById((long) 3).getStartTime());
    }

    @Test
    void create() {
        TimePeriod timePeriod = timePeriodJdbcDao.getById((long) 1);
        timePeriodJdbcDao.create(timePeriod);
        assertEquals(timePeriod.getStartTime(), timePeriodJdbcDao.getById((long) 11).getStartTime());
    }
}
