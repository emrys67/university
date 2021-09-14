package com.foxminded.university.services;

import com.foxminded.university.dao.GroupJdbcDao;
import com.foxminded.university.dao.StudentJdbcDao;
import com.foxminded.university.dao.exceptions.DaoException;
import com.foxminded.university.dao.exceptions.ServiceException;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class.getName());
    private static final String GROUP_DOESNOT_EXIST = "Group with id %s does not exist";
    private GroupJdbcDao groupJdbcDao;
    private StudentJdbcDao studentJdbcDao;

    @Autowired
    public GroupService(GroupJdbcDao groupJdbcDao, StudentJdbcDao studentJdbcDao) {
        this.groupJdbcDao = groupJdbcDao;
        this.studentJdbcDao = studentJdbcDao;
    }


    public void addGroup(String name) {
        logger.debug("Start service for adding group");
        try {
            Group group = new Group(name);
            groupJdbcDao.create(group);
            logger.debug("Group has been added successfully");
        } catch (DaoException exception) {
            String msg = "Group has not been added";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public Group getGroupById(long id) {
        logger.debug("Start service for getting group by id {}", id);
        try {
            return groupJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = "Group has not been gotten";
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void deleteGroupById(long id) {
        logger.debug("Start service for deleting group by id {}", id);
        try {
            groupJdbcDao.getById(id);
        } catch (DaoException exception) {
            String msg = format(GROUP_DOESNOT_EXIST, id);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        groupJdbcDao.delete(id);
        logger.debug("Group id {} has been deleted", id);
    }

    public List<Group> getAllGroups() {
        logger.debug("Start service for getting all groups");
        return groupJdbcDao.getAll();
    }

    public void updateGroup(Group group) {
        logger.debug("Start service for updating group with id {}", group.getId());
        try {
            groupJdbcDao.getById(group.getId());
        } catch (DaoException exception) {
            String msg = format(GROUP_DOESNOT_EXIST, group.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            groupJdbcDao.update(group);
        } catch (DaoException exception) {
            String msg = format("Group with id %s has not been updated", group.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
    }

    public void addStudent(Student student, Group group) {
        logger.debug("Start service for adding student to group");
        try {
            studentJdbcDao.getById(student.getId());
        } catch (DaoException exception) {
            String msg = format("Student with id %s does not exist", group.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        try {
            groupJdbcDao.getById(group.getId());
        } catch (DaoException exception) {
            String msg = format(GROUP_DOESNOT_EXIST, group.getId());
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        groupJdbcDao.addStudentToGroup(student.getId(), group.getId());
    }

    public List<Student> getStudentsFromGroup(long groupId) {
        logger.debug("Start service for getting all students from group");
        try {
            groupJdbcDao.getById(groupId);
        } catch (DaoException exception) {
            String msg = format(GROUP_DOESNOT_EXIST, groupId);
            logger.warn(msg);
            throw new ServiceException(msg, exception);
        }
        return groupJdbcDao.getStudentsFromGroup(groupId);
    }
}
