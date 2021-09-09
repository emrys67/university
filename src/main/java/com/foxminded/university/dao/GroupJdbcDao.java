package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.GroupDao;
import com.foxminded.university.dao.mappers.GroupMapper;
import com.foxminded.university.dao.mappers.StudentMapper;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class GroupJdbcDao implements GroupDao {
    private static final String SQL_FIND_GROUP = "SELECT * FROM groups WHERE id = ?";
    private static final String SQL_UPDATE_GROUP = "UPDATE groups SET name = ? WHERE id = ?";
    private static final String SQL_DELETE_GROUP = "DELETE FROM groups WHERE id = ?";
    private static final String SQL_INSERT_GROUP = "INSERT INTO groups(name) VALUES(?)";
    private static final String SQL_GET_ALL_GROUP = "SELECT * FROM groups";
    private static final String SQL_INSERT_STUDENT = "INSERT INTO students_groups(student_id, group_id) VALUES(?, ?)";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM students_groups WHERE student_id = ? AND group_id = ?";
    private static final String SQL_GET_ALL_STUDENTS = "SELECT * FROM students JOIN students_groups ON student_id = students.id WHERE group_id = ?";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    StudentJdbcDao studentJdbcDao;
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    public GroupJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Group getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_GROUP, groupMapper, id);
    }

    public List<Group> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_GROUP, groupMapper);
    }

    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_GROUP, id);
    }

    public void update(Group group) {
        List<Student> currentSutdents = getStudentsFromGroup(group.getId());
        group.getStudents().stream().filter(student -> !currentSutdents.contains(student)).forEach(student -> addStudentToGroup(student.getId(), group.getId()));
        currentSutdents.stream().filter(student -> !group.getStudents().contains(student)).forEach(student -> deleteStudentFromGroup(student.getId(), group.getId()));
        jdbcTemplate.update(SQL_UPDATE_GROUP, group.getName(), group.getId());
    }

    public void create(Group group) {
        jdbcTemplate.update(SQL_INSERT_GROUP, group.getName());
    }

    public void addStudentToGroup(long studentId, long groupId) {
        jdbcTemplate.update(SQL_INSERT_STUDENT, studentId, groupId);
    }

    public void deleteStudentFromGroup(long studentId, long groupId) {
        jdbcTemplate.update(SQL_DELETE_STUDENT, studentId, groupId);
    }

    public List<Student> getStudentsFromGroup(long groupId) {
        return jdbcTemplate.query(SQL_GET_ALL_STUDENTS, studentMapper, groupId);
    }

    public void updateStudentsInGroup(long groupId) {
        jdbcTemplate.query(SQL_GET_ALL_STUDENTS, studentMapper, groupId);
    }
}
