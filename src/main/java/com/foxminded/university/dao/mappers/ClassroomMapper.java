package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.exceptions.MapperException;
import com.foxminded.university.entities.Classroom;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClassroomMapper implements RowMapper<Classroom> {
    private final static String ID = "id";
    private final static String CAPACITY = "capacity";
    private final static String MAPPER_EXCEPTION = "Exception in MapperClass";
    public Classroom mapRow(ResultSet resultSet, int i){
        try {
            Classroom classroom = new Classroom();
            classroom.setId(resultSet.getLong(ID));
            classroom.setCapacity(resultSet.getInt(CAPACITY));
            return classroom;
        }
        catch (SQLException e){
            throw new MapperException(MAPPER_EXCEPTION, e);
        }
    }
}