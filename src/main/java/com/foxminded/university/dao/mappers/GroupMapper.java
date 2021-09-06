package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.GroupJdbcDao;
import com.foxminded.university.entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupMapper implements RowMapper<Group> {
    private final static String ID = "id";
    private final static String NAME = "name";
    private GroupJdbcDao groupJdbcDao;

    @Autowired
    public GroupMapper(GroupJdbcDao groupJdbcDao) {
        this.groupJdbcDao = groupJdbcDao;
    }

    public Group mapRow(ResultSet resultSet, int i) {
        try {
            Group group = new Group();
            long id = resultSet.getLong(ID);
            group.setId(id);
            group.setName(resultSet.getString(NAME));
            group.setStudents(groupJdbcDao.getStudentsFromGroup(id));
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
