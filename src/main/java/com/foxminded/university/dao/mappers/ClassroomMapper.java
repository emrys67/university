package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.exceptions.MapperException;
import com.foxminded.university.entities.Classroom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClassroomMapper implements RowMapper<Classroom> {
    private static final Logger logger = LoggerFactory.getLogger(ClassroomMapper.class.getName());
    private static final String ID = "id";
    private static final String CAPACITY = "capacity";
    private static final String MAPPER_EXCEPTION = "Exception has occurred during mapRowing Student";

    public Classroom mapRow(ResultSet resultSet, int i) {
        try {
            logger.debug("Start rowMapper with classroom id {}", resultSet.getLong(ID));
            Classroom classroom = new Classroom();
            classroom.setId(resultSet.getLong(ID));
            classroom.setCapacity(resultSet.getInt(CAPACITY));
            return classroom;
        } catch (SQLException e) {
            throw new MapperException(MAPPER_EXCEPTION, e);
        }
    }
}
