package com.foxminded.university.entities;

import java.util.List;
import java.util.Objects;

public class Lecture {
    private long id;
    private Subject subject;
    private List<Group> groups;
    private Teacher teacher;
    private TimePeriod timePeriod;
    private Classroom classroom;

    public Lecture() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return id == lecture.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", subject=" + subject +
                ", groups=" + groups +
                ", teacher=" + teacher +
                ", timePeriod=" + timePeriod +
                ", classroom=" + classroom +
                '}';
    }
}
