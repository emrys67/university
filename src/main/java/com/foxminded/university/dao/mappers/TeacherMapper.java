package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class TeacherMapper implements RowMapper<Teacher> {
    public Teacher mapRow(ResultSet resultSet, int i){
        Teacher teacher = new Teacher();
        return teacher;
    }
}
