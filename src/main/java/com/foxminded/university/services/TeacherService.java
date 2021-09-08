package com.foxminded.university.services;

import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.dao.TimePeriodJdbcDao;
import com.foxminded.university.dao.VacationJdbcDao;
import com.foxminded.university.entities.Teacher;
import com.foxminded.university.entities.TimePeriod;
import com.foxminded.university.entities.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class TeacherService {
    @Autowired
    TeacherJdbcDao teacherJdbcDao;
    @Autowired
    TimePeriodJdbcDao timePeriodJdbcDao;
    @Autowired
    VacationJdbcDao vacationJdbcDao;

    public void addTeacher(String firstname, String lastname, String gender, Date dateOfBirth,
                           long workingHoursId, long vacationId) {
        Vacation vacation = vacationJdbcDao.getById(vacationId);
        TimePeriod workingHours = timePeriodJdbcDao.getById(workingHoursId);
        Teacher teacher = new Teacher(vacation, workingHours, firstname, lastname, dateOfBirth, gender);
        teacherJdbcDao.create(teacher);
    }

    public Teacher getTeacherById(long id) {
        return teacherJdbcDao.getById(id);
    }

    public void deleteTeacherById(long id) {
        teacherJdbcDao.delete(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherJdbcDao.getAll();
    }

    public void updateTeacher(String firstname, String lastname, String gender, Date dateOfBirth,
                              long workingHoursId, long vacationId, long id) {
        Vacation vacation = vacationJdbcDao.getById(vacationId);
        TimePeriod workingHours = timePeriodJdbcDao.getById(workingHoursId);
        Teacher teacher = new Teacher(vacation, workingHours, firstname, lastname, dateOfBirth, gender, id);
        teacherJdbcDao.update(teacher);
    }
}
