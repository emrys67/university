package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.SubjectJdbcDao;
import com.foxminded.university.dao.TeacherJdbcDao;
import com.foxminded.university.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubjectMapper implements RowMapper<Subject> {
    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private TeacherJdbcDao teacherJdbcDao;
    private SubjectJdbcDao subjectJdbcDao;

    @Autowired
    public SubjectMapper(TeacherJdbcDao teacherJdbcDao, SubjectJdbcDao subjectJdbcDao) {
        this.teacherJdbcDao = teacherJdbcDao;
        this.subjectJdbcDao = subjectJdbcDao;
    }

    public Subject mapRow(ResultSet resultSet, int i) {
        try {
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
            throw new RuntimeException();
        }
    }
}
