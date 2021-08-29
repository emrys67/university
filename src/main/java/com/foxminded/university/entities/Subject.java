package com.foxminded.university.entities;

import java.util.List;

public class Subject {
    private String name;
    private String description;
    private Teacher supervisor;
    private List<Teacher> teachers;
    private long id;

    public Subject() {
    }

    public Subject(String name, String description, Teacher supervisor, List<Teacher> teachers) {
        this.name = name;
        this.description = description;
        this.supervisor = supervisor;
        this.teachers = teachers;
    }

    public Subject(String name, String description, Teacher supervisor, List<Teacher> teachers, long id) {
        this.name = name;
        this.description = description;
        this.supervisor = supervisor;
        this.teachers = teachers;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Teacher supervisor) {
        this.supervisor = supervisor;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
