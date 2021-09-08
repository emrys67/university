package com.foxminded.university.services;

import com.foxminded.university.dao.GroupJdbcDao;
import com.foxminded.university.dao.StudentJdbcDao;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupService {
    @Autowired
    GroupJdbcDao groupJdbcDao;
    @Autowired
    StudentJdbcDao studentJdbcDao;

    public void addGroup(String name) {
        Group group = new Group(name);
        groupJdbcDao.create(group);
    }

    public Group getGroupById(long id) {
        return groupJdbcDao.getById(id);
    }

    public void deleteGroupById(long id) {
        groupJdbcDao.delete(id);
    }

    public List<Group> getAllGroups() {
        return groupJdbcDao.getAll();
    }

    public void updateGroup(String name, long id, boolean changeStudents) {
        List<Student> list;
        if (changeStudents) {
            list = null;
        } else {
            list = groupJdbcDao.getById(id).getStudents();
        }
        Group group = new Group(name, list, id);
        groupJdbcDao.update(group);
    }

    public void addStudent(long studentId, long groupId) {
        groupJdbcDao.addStudentToGroup(studentId, groupId);
    }

    public List<Student> getStudentsFromGroup(long groupId) {
        return groupJdbcDao.getStudentsFromGroup(groupId);
    }
}
