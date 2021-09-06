package com.foxminded.university.dao.mappers;

import com.foxminded.university.dao.LectureJdbcDao;
import com.foxminded.university.dao.interfaces.*;
import com.foxminded.university.entities.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LectureMapper implements RowMapper<Lecture> {
    private final static String ID = "id";
    private final static String CLASSROOM = "classroom_id";
    private final static String SUBJECT = "subject_id";
    private final static String TEACHER = "teacher_id";
    private final static String TIME_PERIOD = "time_period_id";

    private GroupDao groupDao;
    private SubjectDao subjectDao;
    private TeacherDao teacherDao;
    private ClassroomDao classroomDao;
    private TimePeriodDao timePeriodDao;
    private LectureJdbcDao lectureJdbcDao;

    @Autowired
    public LectureMapper(GroupDao groupDao, SubjectDao subjectDao, TeacherDao teacherDao, ClassroomDao classroomDao, TimePeriodDao timePeriodDao, LectureJdbcDao lectureJdbcDao) {
        this.groupDao = groupDao;
        this.subjectDao = subjectDao;
        this.teacherDao = teacherDao;
        this.classroomDao = classroomDao;
        this.timePeriodDao = timePeriodDao;
        this.lectureJdbcDao = lectureJdbcDao;
    }

    public Lecture mapRow(ResultSet resultSet, int i) {
        try {
            Lecture lecture = new Lecture();
            long id = resultSet.getLong(ID);
            lecture.setId(id);
            lecture.setClassroom(classroomDao.getById(resultSet.getLong(CLASSROOM)));
            lecture.setSubject(subjectDao.getById(resultSet.getLong(SUBJECT)));
            lecture.setTeacher(teacherDao.getById(resultSet.getLong(TEACHER)));
            lecture.setTimePeriod(timePeriodDao.getById(resultSet.getLong(TIME_PERIOD)));
            lecture.setGroups(lectureJdbcDao.getGroupsFromLecture(id));
            return lecture;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
