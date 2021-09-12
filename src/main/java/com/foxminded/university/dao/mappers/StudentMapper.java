package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.dao.exceptions.MapperException;
import com.foxminded.university.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper<Student> {
    private static final Logger logger = LoggerFactory.getLogger(StudentMapper.class.getName());
    private final static String ID = "id";
    private final static String FIRSTNAME = "firstname";
    private final static String LASTNAME = "lastname";
    private final static String BIRTH_DATE = "date_of_birth";
    private final static String STUDY_YEAR = "study_year";
    private final static String GENDER = "gender";
    private final static String MAPPER_EXCEPTION = "Exception has occurred during mapRowing Student";

    public Student mapRow(ResultSet resultSet, int i) {
        try {
            logger.info("Start rowMapper with student id {}", resultSet.getLong(ID));
            Student student = new Student();
            student.setId(resultSet.getLong(ID));
            student.setFirstname(resultSet.getString(FIRSTNAME));
            student.setLastname(resultSet.getString(LASTNAME));
            student.setDateOfBirth(resultSet.getDate(BIRTH_DATE));
            student.setStudyYear(resultSet.getInt(STUDY_YEAR));
            student.setGender(resultSet.getString(GENDER));
            return student;
        } catch (SQLException e) {
            throw new MapperException(MAPPER_EXCEPTION, e);
        }
    }
}
