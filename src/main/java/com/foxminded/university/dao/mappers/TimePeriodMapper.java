package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.dao.exceptions.MapperException;
import com.foxminded.university.entities.TimePeriod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TimePeriodMapper implements RowMapper<TimePeriod> {
    private static final Logger logger = LoggerFactory.getLogger(TimePeriodMapper.class.getName());
    private final static String ID = "id";
    private final static String START_DATE = "start_date";
    private final static String END_DATE = "end_date";
    private final static String START_TIME = "start_time";
    private final static String END_TIME = "end_time";
    private final static String MAPPER_EXCEPTION = "Exception has occurred during mapRowing TimePeriod";

    public TimePeriod mapRow(ResultSet resultSet, int i) {
        try {
            logger.debug("Start rowMapper with timePeriod id {}", resultSet.getLong(ID));
            TimePeriod timePeriod = new TimePeriod();
            timePeriod.setId(resultSet.getLong(ID));
            timePeriod.setStartDate(resultSet.getDate(START_DATE));
            timePeriod.setEndDate(resultSet.getDate(END_DATE));
            timePeriod.setStartTime(resultSet.getTime(START_TIME).toLocalTime());
            timePeriod.setEndTime(resultSet.getTime(END_TIME).toLocalTime());
            return timePeriod;
        } catch (SQLException e) {
            throw new MapperException(MAPPER_EXCEPTION, e);
        }
    }
}
