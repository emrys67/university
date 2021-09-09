package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.SubjectDao;
import com.foxminded.university.dao.mappers.SubjectMapper;
import com.foxminded.university.dao.mappers.TeacherMapper;
import com.foxminded.university.entities.Subject;
import com.foxminded.university.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class SubjectJdbcDao implements SubjectDao {
    private static final String SQL_FIND_SUBJECT = "SELECT * FROM subjects WHERE id = ?";
    private static final String SQL_UPDATE_SUBJECT = "UPDATE subjects SET name = ?, description = ?, supervisor_id = ?" +
            " WHERE id = ?";
    private static final String SQL_DELETE_SUBJECT = "DELETE FROM subjects WHERE id = ?";
    private static final String SQL_INSERT_SUBJECT = "INSERT INTO subjects(name, description, supervisor_id) VALUES(?, ?, ?)";
    private static final String SQL_GET_ALL_SUBJECT = "SELECT * FROM subjects";
    private static final String SQL_GET_ALL_TEACHERS = "SELECT * FROM teachers JOIN subjects_teachers ON teachers.id = " +
            "subjects_teachers.teacher_id WHERE subject_id = ?";
    private static final String SQL_ADD_TEACHER = "INSERT INTO subjects_teachers(teacher_id, subject_id) VALUES (?, ?)";
    private static final String SQL_DELETE_TEACHER = "DELETE FROM subjects_teachers WHERE teacher_id = ? AND subject_id = ?";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    public SubjectJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Subject getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_SUBJECT, subjectMapper, id);
    }

    public List<Subject> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_SUBJECT, subjectMapper);
    }

    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_SUBJECT, id);
    }

    public void update(Subject subject) {
        List<Teacher> currentTeachers = getTeachersFromSubject(subject.getId());
        subject.getTeachers().stream().filter(teacher -> !currentTeachers.contains(teacher)).forEach(teacher -> addTeacherToSubject(teacher.getId(), subject.getId()));
        currentTeachers.stream().filter(teacher -> !subject.getTeachers().contains(teacher)).forEach(teacher -> deleteTeacherFromSubject(teacher.getId(), subject.getId()));
        jdbcTemplate.update(SQL_UPDATE_SUBJECT, subject.getName(), subject.getDescription(), subject.getSupervisor().getId(),
                subject.getId());
    }

    public void create(Subject subject) {
        jdbcTemplate.update(SQL_INSERT_SUBJECT, subject.getName(), subject.getDescription(), subject.getSupervisor().getId());
    }

    public void addTeacherToSubject(long teacherId, long subjectId) {
        jdbcTemplate.update(SQL_ADD_TEACHER, teacherId, subjectId);
    }

    public void deleteTeacherFromSubject(long teacherId, long subjectId) {
        jdbcTemplate.update(SQL_DELETE_TEACHER, teacherId, subjectId);
    }

    public List<Teacher> getTeachersFromSubject(long subjectId) {
        return jdbcTemplate.query(SQL_GET_ALL_TEACHERS, teacherMapper, subjectId);
    }
}
