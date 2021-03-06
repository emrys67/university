package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.exceptions.MapperException;
import com.foxminded.university.dao.interfaces.TimePeriodDao;
import com.foxminded.university.dao.interfaces.VacationDao;
import com.foxminded.university.entities.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeacherMapper implements RowMapper<Teacher> {
    private static final Logger logger = LoggerFactory.getLogger(TeacherMapper.class.getName());
    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String GENDER = "gender";
    private static final String VACATION = "vacation_id";
    private static final String BIRTH_DATE = "date_of_birth";
    private static final String WORKING_HOURS = "working_hours_id";
    private static final String MAPPER_EXCEPTION = "Exception has occurred during mapRowing Teacher";
    private TimePeriodDao timePeriodDao;
    private VacationDao vacationDao;

    @Autowired
    public TeacherMapper(TimePeriodDao timePeriodDao, VacationDao vacationDao) {
        this.timePeriodDao = timePeriodDao;
        this.vacationDao = vacationDao;
    }

    public Teacher mapRow(ResultSet resultSet, int i) {
        try {
            logger.debug("Start rowMapper with teacher id {}", resultSet.getLong(ID));
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
            throw new MapperException(MAPPER_EXCEPTION, e);
        }
    }
}
