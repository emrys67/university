package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.Lecture;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class LectureMapper implements RowMapper<Lecture> {
    public Lecture mapRow(ResultSet resultSet, int i){
        Lecture lecture = new Lecture();
        return lecture;
    }
}
