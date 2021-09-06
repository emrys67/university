package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.interfaces.TimePeriodDao;
import com.foxminded.university.dao.interfaces.VacationDao;
import com.foxminded.university.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeacherMapper implements RowMapper<Teacher> {
    private final static String ID = "id";
    private final static String FIRSTNAME = "firstname";
    private final static String LASTNAME = "lastname";
    private final static String GENDER = "gender";
    private final static String VACATION = "vacation_id";
    private final static String BIRTH_DATE = "date_of_birth";
    private final static String WORKING_HOURS = "working_hours_id";
    private TimePeriodDao timePeriodDao;
    private VacationDao vacationDao;

    @Autowired
    public TeacherMapper(TimePeriodDao timePeriodDao, VacationDao vacationDao) {
        this.timePeriodDao = timePeriodDao;
        this.vacationDao = vacationDao;
    }

    public Teacher mapRow(ResultSet resultSet, int i) {
        try {
            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getLong(ID));
            teacher.setFirstname(resultSet.getString(FIRSTNAME));
            teacher.setLastname(resultSet.getString(LASTNAME));
            teacher.setGender(resultSet.getString(GENDER));
            teacher.setVacation(vacationDao.getById(resultSet.getLong(VACATION)));
            teacher.setDateOfBirth(resultSet.getDate(BIRTH_DATE));
            teacher.setWorkingHours(timePeriodDao.getById(resultSet.getLong(WORKING_HOURS)));
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
