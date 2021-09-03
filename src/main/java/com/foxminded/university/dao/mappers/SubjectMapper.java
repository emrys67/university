package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class SubjectMapper implements RowMapper<Subject> {
    public Subject mapRow(ResultSet resultSet, int i){
        Subject subject = new Subject();
        return subject;
    }
}
