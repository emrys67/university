package com.foxminded.university.entities;

import java.util.List;

public class Lecture {
    private Subject subject;
    private List<Group> groups;
    private Teacher teacher;
    private TimePeriod timePeriod;
    private Classroom classroom;
    private long id;

    public Lecture(Subject subject) {
        this.subject = subject;
    }

    public Lecture(Subject subject, List<Group> groups, Teacher teacher, TimePeriod timePeriod, Classroom classroom) {
        this.subject = subject;
        this.groups = groups;
        this.teacher = teacher;
        this.timePeriod = timePeriod;
        this.classroom = classroom;
    }

    public Lecture(Subject subject, List<Group> groups, Teacher teacher, TimePeriod timePeriod, Classroom classroom, long id) {
        this.subject = subject;
        this.groups = groups;
        this.teacher = teacher;
        this.timePeriod = timePeriod;
        this.classroom = classroom;
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
