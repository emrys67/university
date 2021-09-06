package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.Vacation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class VacationMapper implements RowMapper<Vacation> {
    public Vacation mapRow(ResultSet resultSet, int i){
        Vacation vacation = new Vacation();
        return vacation;
    }
}
