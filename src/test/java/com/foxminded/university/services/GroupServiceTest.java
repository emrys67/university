package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.GroupJdbcDao;
import com.foxminded.university.dao.StudentJdbcDao;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
class GroupServiceTest {
    @Mock
    private GroupJdbcDao groupJdbcDaoMock;
    @Mock
    private StudentJdbcDao studentJdbcDaoMock;
    @Autowired
    private GroupJdbcDao groupJdbcDao;
    @Autowired
    private StudentJdbcDao studentJdbcDao;
    @InjectMocks
    private GroupService groupService;

    @Test
    void addGroupDaoWasUsed() {
        groupService.addGroup(any());
        verify(groupJdbcDaoMock, times(1)).create(any());
    }

    @Test
    void getGroupByIdDaoWasUsed() {
        groupService.getGroupById(1);
        verify(groupJdbcDaoMock, times(1)).getById(any());
    }

    @Test
    void deleteGroupByIdDaoWasUsed() {
        groupService.deleteGroupById((long) 1);
        verify(groupJdbcDaoMock, times(1)).delete(any());
    }

    @Test
    void getAllGroupsDaoWasUsed() {
        groupService.getAllGroups();
        verify(groupJdbcDaoMock, times(1)).getAll();
    }

    @Test
    void updateGroupDaoWasUsed() {
        Group group = groupJdbcDao.getById((long) 1);
        groupService.updateGroup(group);
        verify(groupJdbcDaoMock, times(1)).update(any());
    }

    @Test
    void addStudentDaoWasUsed() {
        Student student = studentJdbcDao.getById((long) 1);
        Group group = groupJdbcDao.getById((long) 1);
        groupService.addStudent(student, group);
        verify(groupJdbcDaoMock, times(1)).addStudentToGroup((long) 1, (long) 1);
    }

    @Test
    void getStudentsFromGroupDaoWasUsed() {
        groupService.getStudentsFromGroup((long) 1);
        verify(groupJdbcDaoMock, times(1)).getStudentsFromGroup((long) 1);
    }
}
