package com.foxminded.university.services;

import com.foxminded.university.dao.GroupJdbcDao;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private GroupJdbcDao groupJdbcDao;

    @Autowired
    public GroupService(GroupJdbcDao groupJdbcDao) {
        this.groupJdbcDao = groupJdbcDao;
    }

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

    public void updateGroup(Group group) {
        groupJdbcDao.update(group);
    }

    public void addStudent(Student student, Group group) {
        groupJdbcDao.addStudentToGroup(student.getId(), group.getId());
    }

    public List<Student> getStudentsFromGroup(long groupId) {
        return groupJdbcDao.getStudentsFromGroup(groupId);
    }
}
