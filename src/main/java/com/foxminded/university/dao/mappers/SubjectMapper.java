package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.SubjectJdbcDao;
import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.dao.exceptions.MapperException;
import com.foxminded.university.entities.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubjectMapper implements RowMapper<Subject> {
    private static final Logger logger = LoggerFactory.getLogger(SubjectMapper.class.getName());
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String MAPPER_EXCEPTION = "Exception has occurred during mapRowing Subject";
    private TeacherJdbcDao teacherJdbcDao;
    private SubjectJdbcDao subjectJdbcDao;

    @Autowired
    public SubjectMapper(TeacherJdbcDao teacherJdbcDao, SubjectJdbcDao subjectJdbcDao) {
        this.teacherJdbcDao = teacherJdbcDao;
        this.subjectJdbcDao = subjectJdbcDao;
    }

    public Subject mapRow(ResultSet resultSet, int i) {
        try {
            logger.debug("Start rowMapper with subject id {}", resultSet.getLong(ID));
            Subject subject = new Subject();
            long id = resultSet.getLong(ID);
            subject.setId(id);
            subject.setName(resultSet.getString(NAME));
            subject.setDescription(resultSet.getString(DESCRIPTION));
            subject.setSupervisor(teacherJdbcDao.getById(resultSet.getLong(ID)));
            subject.setTeachers(subjectJdbcDao.getTeachersFromSubject(id));
            return subject;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MapperException(MAPPER_EXCEPTION, e);
        }
    }
}
