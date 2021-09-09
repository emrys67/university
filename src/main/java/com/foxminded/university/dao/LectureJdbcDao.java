package com.foxminded.university.dao;

import com.foxminded.university.dao.interfaces.LectureDao;
import com.foxminded.university.dao.mappers.GroupMapper;
import com.foxminded.university.dao.mappers.LectureMapper;
import com.foxminded.university.entities.Group;
import com.foxminded.university.entities.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class LectureJdbcDao implements LectureDao {
    private static final String SQL_FIND_LECTURE = "SELECT * FROM lectures WHERE id = ?";
    private static final String SQL_UPDATE_LECTURE = "UPDATE lectures SET subject_id = ?, teacher_id = ?, time_period_id" +
            " = ?, classroom_id = ? WHERE id = ?";
    private static final String SQL_DELETE_LECTURE = "DELETE FROM lectures WHERE id = ?";
    private static final String SQL_INSERT_LECTURE = "INSERT INTO lectures(teacher_id, time_period_id, classroom_id, subject_id) VALUES(?, ?, ?, ?)";
    private static final String SQL_GET_ALL_LECTURE = "SELECT * FROM lectures";
    private static final String SQL_GET_ALL_GROUPS = "SELECT * FROM groups JOIN lectures_groups ON groups.id = lectures_groups.group_id WHERE lecture_id = ?";
    private static final String SQL_ADD_GROUP = "INSERT INTO lectures_groups(lecture_id, group_id) VALUES (?, ?)";
    private static final String SQL_DELETE_GROUP = "DELETE FROM lectures_groups WHERE lecture_id = ? AND group_id = ?";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LectureMapper lectureMapper;
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    public LectureJdbcDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Lecture getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_LECTURE, lectureMapper, id);
    }

    public List<Lecture> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_LECTURE, lectureMapper);
    }

    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_LECTURE, id);
    }

    public void update(Lecture lecture) {
        List<Group> currentGroups = getGroupsFromLecture(lecture.getId());
        lecture.getGroups().stream().filter(group -> !currentGroups.contains(group)).forEach(group -> addGroup(group.getId(), lecture.getId()));
        currentGroups.stream().filter(group -> !lecture.getGroups().contains(group)).forEach(group -> deleteGroup(group.getId(), lecture.getId()));
        jdbcTemplate.update(SQL_UPDATE_LECTURE, lecture.getSubject().getId(), lecture.getTeacher().getId(),
                lecture.getTimePeriod().getId(), lecture.getClassroom().getId(), lecture.getId());
    }

    public void create(Lecture lecture) {
        jdbcTemplate.update(SQL_INSERT_LECTURE, lecture.getTeacher().getId(), lecture.getTimePeriod().getId(),
                lecture.getClassroom().getId(), lecture.getSubject().getId());
    }

    public void addGroup(long lectureId, long groupId) {
        jdbcTemplate.update(SQL_ADD_GROUP, lectureId, groupId);
    }

    public void deleteGroup(long lectureId, long groupId) {
        jdbcTemplate.update(SQL_DELETE_GROUP, lectureId, groupId);
    }

    public List<Group> getGroupsFromLecture(long lectureId) {
        return jdbcTemplate.query(SQL_GET_ALL_GROUPS, groupMapper, lectureId);
    }
}
