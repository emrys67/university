package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.TimePeriod;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class TimePeriodMapper implements RowMapper<TimePeriod> {
    public TimePeriod mapRow(ResultSet resultSet, int i) {
        TimePeriod timePeriod = new TimePeriod();
        return timePeriod;
    }
}
