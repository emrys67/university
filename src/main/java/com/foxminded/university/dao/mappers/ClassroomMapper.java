package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.Classroom;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class ClassroomMapper implements RowMapper<Classroom> {
    public Classroom mapRow(ResultSet resultSet, int i){
        Classroom classroom = new Classroom();
        return classroom;
    }
}
