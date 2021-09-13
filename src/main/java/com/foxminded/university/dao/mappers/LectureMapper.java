package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.dao.LectureJdbcDao;
import com.foxminded.university.dao.exceptions.MapperException;
import com.foxminded.university.dao.interfaces.*;
import com.foxminded.university.entities.Lecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LectureMapper implements RowMapper<Lecture> {
    private static final Logger logger = LoggerFactory.getLogger(LectureMapper.class.getName());
    private static final String ID = "id";
    private static final String CLASSROOM = "classroom_id";
    private static final String SUBJECT = "subject_id";
    private static final String TEACHER = "teacher_id";
    private static final String TIME_PERIOD = "time_period_id";
    private static final String MAPPER_EXCEPTION = "Exception has occurred during mapRowing Lecture";

    private SubjectDao subjectDao;
    private TeacherDao teacherDao;
    private ClassroomDao classroomDao;
    private TimePeriodDao timePeriodDao;
    private LectureJdbcDao lectureJdbcDao;

    @Autowired
    public LectureMapper(SubjectDao subjectDao, TeacherDao teacherDao, ClassroomDao classroomDao, TimePeriodDao timePeriodDao, LectureJdbcDao lectureJdbcDao) {
        this.subjectDao = subjectDao;
        this.teacherDao = teacherDao;
        this.classroomDao = classroomDao;
        this.timePeriodDao = timePeriodDao;
        this.lectureJdbcDao = lectureJdbcDao;
    }

    public Lecture mapRow(ResultSet resultSet, int i) {
        try {
            logger.debug("Start rowMapper with lecture id {}", resultSet.getLong(ID));
            long id = resultSet.getLong(ID);
            return new Lecture().setId(id).setClassroom(classroomDao.getById(resultSet.getLong(CLASSROOM)))
                    .setSubject(subjectDao.getById(resultSet.getLong(SUBJECT)))
                    .setTeacher(teacherDao.getById(resultSet.getLong(TEACHER)))
                    .setTimePeriod(timePeriodDao.getById(resultSet.getLong(TIME_PERIOD)))
                    .setGroups(lectureJdbcDao.getGroupsFromLecture(id));
        } catch (SQLException e) {
            throw new MapperException(MAPPER_EXCEPTION, e);
        }
    }
}
