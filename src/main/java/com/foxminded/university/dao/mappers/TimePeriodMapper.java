package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.TimePeriod;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TimePeriodMapper implements RowMapper<TimePeriod> {
    private final static String ID = "id";
    private final static String START_DATE = "start_date";
    private final static String END_DATE = "end_date";
    private final static String START_TIME = "start_time";
    private final static String END_TIME = "end_time";

    public TimePeriod mapRow(ResultSet resultSet, int i) {
        try {
            TimePeriod timePeriod = new TimePeriod();
            timePeriod.setId(resultSet.getLong(ID));
            timePeriod.setStartDate(resultSet.getDate(START_DATE));
            timePeriod.setEndDate(resultSet.getDate(END_DATE));
            timePeriod.setStartTime(resultSet.getTime(START_TIME).toLocalTime());
            timePeriod.setEndTime(resultSet.getTime(END_TIME).toLocalTime());
            return timePeriod;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
