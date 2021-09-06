package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class StudentMapper implements RowMapper<Student> {
    public Student mapRow(ResultSet resultSet, int i){
        Student student = new Student();
        return student;
    }
}
