package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.GroupDao;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupJdbcDao implements GroupDao {
    private final static String SQL_FIND_CLASSROOM = "";
    private final static String SQL_UPDATE_CLASSROOM = "";
    private final static String SQL_DELETE_CLASSROOM = "";
    private final static String SQL_INSERT_CLASSROOM = "";
    private final static String SQL_GET_ALL_CLASSROOM = "";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public GroupJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Group getById(Long id){
        return new Group();
    }

    public List<Group> getAll(){
        return new ArrayList<>();
    }

    public boolean delete(Group group){
        return true;
    }

    public boolean update(Group group){
        return true;
    }

    public boolean create(Group group){
        return true;
    }
}
