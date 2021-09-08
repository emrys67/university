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

    public Lecture setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Lecture setGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Lecture setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public Lecture setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
        return this;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public Lecture setClassroom(Classroom classroom) {
        this.classroom = classroom;
        return this;
    }

    public long getId() {
        return id;
    }

    public Lecture setId(long id) {
        this.id = id;
        return this;
    }

    public Lecture createLecture() {
        return new Lecture(subject, groups, teacher, timePeriod, classroom, id);
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
