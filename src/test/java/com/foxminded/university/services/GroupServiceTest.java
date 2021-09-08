package com.foxminded.university.services;

import com.foxminded.university.config.TestConfig;
import com.foxminded.university.dao.GroupJdbcDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringJUnitConfig(TestConfig.class)
public class GroupServiceTest {
    @Mock
    private GroupJdbcDao groupJdbcDao;
    @InjectMocks
    private GroupService groupService;

    @Test
    public void addGroupDaoWasUsed() {
        doNothing().when(groupJdbcDao).create(any());
        groupService.addGroup("gh");
        verify(groupJdbcDao, times(1)).create(any());
    }

    @Test
    public void getGroupByIdDaoWasUsed() {
        when(groupJdbcDao.getById(any())).thenReturn(any());
        groupService.getGroupById(1);
        verify(groupJdbcDao, times(1)).getById(any());
    }

    @Test
    public void deleteGroupByIdDaoWasUsed() {
        doNothing().when(groupJdbcDao).delete(any());
        groupService.deleteGroupById((long) 1);
        verify(groupJdbcDao, times(1)).delete(any());
    }

    @Test
    public void getAllGroupsDaoWasUsed() {
        when(groupJdbcDao.getAll()).thenReturn(new ArrayList<>());
        groupService.getAllGroups();
        verify(groupJdbcDao, times(1)).getAll();
    }

    @Test
    public void updateGroupDaoWasUsed() {
        doNothing().when(groupJdbcDao).update(any());
        groupService.updateGroup("ff", (long) 1, true);
        verify(groupJdbcDao, times(1)).update(any());
    }

    @Test
    public void addStudentDaoWasUsed() {
        doNothing().when(groupJdbcDao).addStudentToGroup((long) 1, (long) 1);
        groupService.addStudent((long) 1, (long) 1);
        verify(groupJdbcDao, times(1)).addStudentToGroup((long) 1, (long) 1);
    }

    @Test
    public void getStudentsFromGroupDaoWasUsed() {
        when(groupJdbcDao.getStudentsFromGroup((long) 1)).thenReturn(new ArrayList<>());
        groupService.getStudentsFromGroup((long) 1);
        verify(groupJdbcDao, times(1)).getStudentsFromGroup((long) 1);
    }
}
