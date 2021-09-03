package com.foxminded.university.dao.mappers;

import com.foxminded.university.entities.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class GroupMapper implements RowMapper<Group> {
    public Group mapRow(ResultSet resultSet, int i){
        Group group = new Group();
        return group;
    }
}
