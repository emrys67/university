package com.foxminded.university.entities;

import java.util.List;

public class Group {
    private String name;
    private List<Student> students;
    private long id;

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Group(String name, List<Student> students, long id) {
        this.name = name;
        this.students = students;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
